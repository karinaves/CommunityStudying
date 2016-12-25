package com.tau.commstudy.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "test_questions", uniqueConstraints = @UniqueConstraint(columnNames = { "test", "questionNumber" }))
@XmlRootElement
public class TestQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer questionNumber;

    private int answers;

    private int votes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "test_questions_to_tags", joinColumns = @JoinColumn(name = "test_question_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tags;

    @ManyToOne
    private Test test;

    public TestQuestion() {
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public int getAnswers() {
	return answers;
    }

    public void setAnswers(int answers) {
	this.answers = answers;
    }

    public int getVotes() {
	return votes;
    }

    public void setVotes(int votes) {
	this.votes = votes;
    }

    public Set<Tag> getTags() {
	return tags;
    }

    public void setTags(Set<Tag> tags) {
	this.tags = tags;
    }

    public Test getTest() {
	return test;
    }

    public void setTest(Test test) {
	this.test = test;
    }

    public int getNumberInTest() {
	return questionNumber;
    }

    public void setNumberInTest(Integer numberInTest) {
	this.questionNumber = numberInTest;
    }

}
