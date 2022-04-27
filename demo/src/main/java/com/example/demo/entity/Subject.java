package com.example.demo.entity;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jrojas
 */
@Entity
@Table(name = "subject")
@NamedQueries({
        @NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s"),
        @NamedQuery(name = "Subject.findBySubjectId", query = "SELECT s FROM Subject s WHERE s.subjectId = :subjectId"),
        @NamedQuery(name = "Subject.findByName", query = "SELECT s FROM Subject s WHERE s.name = :name"),
        @NamedQuery(name = "Subject.findBySemester", query = "SELECT s FROM Subject s WHERE s.semester = :semester"),
        @NamedQuery(name = "Subject.findByCredits", query = "SELECT s FROM Subject s WHERE s.credits = :credits"),
        @NamedQuery(name = "Subject.findByStatus", query = "SELECT s FROM Subject s WHERE s.status = :status")})
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "subject_id")
    private Integer subjectId;
    @Column(name = "name")
    private String name;
    @Column(name = "semester")
    private Integer semester;
    @Column(name = "credits")
    private Integer credits;
    @Column(name = "status")
    private Short status;
    @OneToMany(mappedBy = "subjectId", fetch = FetchType.LAZY)
    private List<StudentSubject> studentSubjectList;
    @OneToMany(mappedBy = "subjectId", fetch = FetchType.LAZY)
    private List<SubjectTeacher> subjectTeacherList;

    public Subject() {
    }

    public Subject(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public List<StudentSubject> getStudentSubjectList() {
        return studentSubjectList;
    }

    public void setStudentSubjectList(List<StudentSubject> studentSubjectList) {
        this.studentSubjectList = studentSubjectList;
    }

    public List<SubjectTeacher> getSubjectTeacherList() {
        return subjectTeacherList;
    }

    public void setSubjectTeacherList(List<SubjectTeacher> subjectTeacherList) {
        this.subjectTeacherList = subjectTeacherList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectId != null ? subjectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.subjectId == null && other.subjectId != null) || (this.subjectId != null && !this.subjectId.equals(other.subjectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", name='" + name + '\'' +
                ", semester=" + semester +
                ", credits=" + credits +
                ", status=" + status +
                '}';
    }
}
