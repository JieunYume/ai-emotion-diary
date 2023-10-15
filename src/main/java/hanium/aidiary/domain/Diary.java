package hanium.aidiary.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "diaries")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String moodEmojiName;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column
    private LocalDateTime updateDate;

    @Column(nullable = false)
    private String thing;

    @Column(nullable = false)
    private String impression;

    @ColumnDefault("0") // 디폴트값
    private int likeCount;

    @Builder.Default
    @OneToMany(mappedBy = "diary")
    private List<Comment> comments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "diary")
    private List<Like> likes = new ArrayList<>();

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", member=" + member +
                ", moodEmoji=" + moodEmojiName +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", thing='" + thing + '\'' +
                ", impression='" + impression + '\'' +
                ", comments=" + comments +
                '}';
    }

    public void addLike() {
        this.likeCount++;
    }

    public void deleteLike() {
        this.likeCount--;
    }

    public boolean haveComment() {
        if (this.comments == null) {
            return false;
        } else{
            return true;
        }
    }
}
