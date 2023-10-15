package hanium.aidiary.service;

import com.amazonaws.services.s3.AmazonS3;
import hanium.aidiary.domain.Group;
import hanium.aidiary.domain.Member;
import hanium.aidiary.dto.group.*;
import hanium.aidiary.dto.group.GroupFindMembersResponse.GroupMemberDto;
import hanium.aidiary.exception.CustomException;
import hanium.aidiary.repository.GroupRepository;
import hanium.aidiary.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static hanium.aidiary.handler.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;
    private final AmazonS3 amazonS3;

    private int codeLength = 8;
    private final char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

    // 그룹 생성
    @Transactional
    public GroupResponse createGroup(GroupCreateRequest request) {
        Member member = memberRepository.findById(request.getCreatorId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        String invitationCode = excuteGenerate();

        Group group = groupRepository.save(
                Group.builder()
                        .name(request.getName())
                        .invitationCode(invitationCode)
                        .members(new ArrayList<>())
                        .build());

        // 회원의 group_id 수정
        member.setGroup(group);
        memberRepository.save(member);

        return GroupResponse.builder()
                .groupId(group.getId())
                .name(group.getName())
                .invitationCode(group.getInvitationCode())
                .build();
    }

    public String excuteGenerate() {
        Random random = new Random(System.currentTimeMillis());
        int tablelength = characterTable.length;
        StringBuffer invitationCode = new StringBuffer();

        for(int i = 0; i < codeLength; i++) {
            invitationCode.append(characterTable[random.nextInt(tablelength)]);
        }

        return invitationCode.toString();
    }

    // 그룹 가입
// orElseThrow는 Optional의 인자가 null일 경우 예외처리를 시킨다.
    @Transactional
    public Object joinGroup(GroupJoinRequest request) {
        Group group = groupRepository.findByInvitationCode(request.getInvitationCode())
                .orElseThrow(() -> new CustomException(GROUP_NOT_FOUND));
        Member joinMember = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        joinMember.setGroup(group);
        memberRepository.save(joinMember);

        //System.out.println("그룹 멤버:" + group.getMembers().toArray());

        return GroupResponse.builder()
                .groupId(group.getId())
                .name(group.getName())
                .invitationCode(request.getInvitationCode())
                .build();
    }



    // 그룹 멤버 조회
    @Transactional(readOnly = true)
    public GroupFindMembersResponse findGroupMembers(Long memberId) throws IllegalArgumentException{
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        if (member.getGroup() == null) {
            throw new CustomException(GROUP_NOT_EXIST);
        }

        List<Member> members = member.getGroup().getMembers();
        Long groupId = member.getGroup().getId();
        System.out.println(members.size());;

        List<GroupMemberDto> groupMemberList = new ArrayList<>();
        for (Member groupMember : members) {
            if (groupMember.getId() == member.getId()) { // 사용자 자신은 제외
                continue;
            }
            URL url = amazonS3.getUrl("aidiary-bucket", groupMember.getFile().getOrigFilename());
            String urltext = ""+url;

            groupMemberList.add(GroupMemberDto.builder()
                    .memberId(groupMember.getId())
                    .groupId(groupId)
                    .nickName(groupMember.getNickName())
                    .imagePath(urltext)
                    .build());
        }
        return GroupFindMembersResponse.builder()
                .memberNum(members.size())
                .groupFindMemberList(groupMemberList)
                .build();
    }

    // 그룹 나가기
    public Object leaveGroup(GroupRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        if (member.getGroup() == null) {
            throw new CustomException(GROUP_NOT_EXIST);
        }

        member.outGroup();
        memberRepository.save(member);

        // 그룹 멤버가 0명이면 그룹 삭제하는 코드 추후에 업데이트하기

        return "그룹 탈퇴 완료";
    }
}
