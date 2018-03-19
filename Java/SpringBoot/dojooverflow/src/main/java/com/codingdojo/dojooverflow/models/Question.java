package com.codingdojo.dojooverflow.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String content;

    @OneToMany(mappedBy="question")
    private List<Answer> answers;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="tags_questions",
            joinColumns = @JoinColumn(name="question_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id")
    )

    @NotNull(message="Please enter at least one tag")
    @Size(max=3,message="Please do not enter more than three tags")
    private List<Tag> tags;

    @Column(updatable = false)
    @DateTimeFormat(pattern="MM/dd/yyyy HH:mm:ss")
    private Date createdAt;

    @Column
    @DateTimeFormat(pattern="MM/dd/yyyy HH:mm:ss")
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String tagString() {
        if (tags == null) {
            return "";
        } else {
            ArrayList<String> tagsList = new ArrayList<>();
            for (Tag tag : tags) {
                tagsList.add(tag.getSubject());
            }
            return String.join(", ",tagsList);
        }
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
