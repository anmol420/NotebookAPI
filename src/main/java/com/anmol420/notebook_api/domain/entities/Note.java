package com.anmol420.notebook_api.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "notes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String content;

    @Column(nullable = false)
    private boolean isPinned;

    @Column(nullable = false)
    private boolean isArchived;

    @Column(nullable = false)
    private boolean isDeleted;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return isPinned == note.isPinned && isArchived == note.isArchived && isDeleted == note.isDeleted && Objects.equals(id, note.id) && Objects.equals(title, note.title) && Objects.equals(content, note.content) && Objects.equals(createdAt, note.createdAt) && Objects.equals(updatedAt, note.updatedAt) && Objects.equals(user, note.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, isPinned, isArchived, isDeleted, createdAt, updatedAt, user);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isPinned=" + isPinned +
                ", isArchived=" + isArchived +
                ", isDeleted=" + isDeleted +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user=" + user +
                '}';
    }

}
