package com.example.demo.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jrojas
 */
@Entity
@Table(name = "subject_teacher")
@NamedQueries({
        @NamedQuery(name = "SubjectTeacher.findAll", query = "SELECT s FROM SubjectTeacher s"),
        @NamedQuery(name = "SubjectTeacher.findBySubjectTeacherId", query = "SELECT s FROM SubjectTeacher s WHERE s.subjectTeacherId = :subjectTeacherId"),
        @NamedQuery(name = "SubjectTeacher.findByDate", query = "SELECT s FROM SubjectTeacher s WHERE s.date = :date")})
public class SubjectTeacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "subject_teacher_id")
    private Integer subjectTeacherId;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToMany(mappedBy = "subjectTeacherId", fetch = FetchType.LAZY)
    private List<StudentSubject> studentSubjectList;
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subjectId;
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacherId;

    public SubjectTeacher() {
    }

    public SubjectTeacher(Integer subjectTeacherId) {
        this.subjectTeacherId = subjectTeacherId;
    }

    public Integer getSubjectTeacherId() {
        return subjectTeacherId;
    }

    public void setSubjectTeacherId(Integer subjectTeacherId) {
        this.subjectTeacherId = subjectTeacherId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<StudentSubject> getStudentSubjectList() {
        return studentSubjectList;
    }

    public void setStudentSubjectList(List<StudentSubject> studentSubjectList) {
        this.studentSubjectList = studentSubjectList;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectTeacherId != null ? subjectTeacherId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubjectTeacher)) {
            return false;
        }
        SubjectTeacher other = (SubjectTeacher) object;
        if ((this.subjectTeacherId == null && other.subjectTeacherId != null) || (this.subjectTeacherId != null && !this.subjectTeacherId.equals(other.subjectTeacherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SubjectTeacher{" +
                "subjectTeacherId=" + subjectTeacherId +
                ", date=" + date +
                '}';
    }
}
