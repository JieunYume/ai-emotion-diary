package hanium.aidiary.dto.group;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupFindMembersResponse {
    private int memberNum;
    private List<GroupMemberDto> groupFindMemberList;

    @Getter
    @Setter
    public static class GroupMemberDto {
        private Long memberId;
        private Long groupId;
        private String nickName;
        private String imagePath;

        @Builder
        public GroupMemberDto(Long memberId, Long groupId, String nickName, String imagePath) {
            this.memberId = memberId;
            this.groupId = groupId;
            this.nickName = nickName;
            this.imagePath = imagePath;
        }
    }

}
