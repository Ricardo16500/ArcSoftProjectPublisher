package com.example.demo.entity;


import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jrojas
 */
@Entity
@Table(name = "student_subject")
@NamedQueries({
        @NamedQuery(name = "StudentSubject.findAll", query = "SELECT s FROM StudentSubject s"),
        @NamedQuery(name = "StudentSubject.findByStudentSubjectId", query = "SELECT s FROM StudentSubject s WHERE s.studentSubjectId = :studentSubjectId"),
        @NamedQuery(name = "StudentSubject.findByDate", query = "SELECT s FROM StudentSubject s WHERE s.date = :date")})
public class StudentSubject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "student_subject_id")
    private Integer studentSubjectId;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student studentId;
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subjectId;
    @JoinColumn(name = "subject_teacher_id", referencedColumnName = "subject_teacher_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SubjectTeacher subjectTeacherId;

    public StudentSubject() {
    }

    public StudentSubject(Integer studentSubjectId) {
        this.studentSubjectId = studentSubjectId;
    }

    public Integer getStudentSubjectId() {
        return studentSubjectId;
    }

    public void setStudentSubjectId(Integer studentSubjectId) {
        this.studentSubjectId = studentSubjectId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    public SubjectTeacher getSubjectTeacherId() {
        return subjectTeacherId;
    }

    public void setSubjectTeacherId(SubjectTeacher subjectTeacherId) {
        this.subjectTeacherId = subjectTeacherId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentSubjectId != null ? studentSubjectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentSubject)) {
            return false;
        }
        StudentSubject other = (StudentSubject) object;
        if ((this.studentSubjectId == null && other.studentSubjectId != null) || (this.studentSubjectId != null && !this.studentSubjectId.equals(other.studentSubjectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StudentSubject{" +
                "studentSubjectId=" + studentSubjectId +
                ", date=" + date +
                '}';
    }
}

