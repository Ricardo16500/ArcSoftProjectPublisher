/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     4/11/2022 10:33:33 PM                        */
/*==============================================================*/


drop table STUDENT;

drop table STUDENT_SUBJECT;

drop table SUBJECT;

drop table SUBJECT_TEACHER;

drop table TEACHER;

/*==============================================================*/
/* Table: STUDENT                                               */
/*==============================================================*/
create table STUDENT (
   STUDENT_ID           SERIAL               not null,
   NAME                 VARCHAR(250)         null,
   LAST_NAME            VARCHAR(250)         null,
   EMAIL                VARCHAR(250)         null,
   constraint PK_STUDENT primary key (STUDENT_ID)
);

/*==============================================================*/
/* Table: STUDENT_SUBJECT                                       */
/*==============================================================*/
create table STUDENT_SUBJECT (
   STUDENT_SUBJECT_ID   SERIAL               not null,
   SUBJECT_ID           INT4                 null,
   SUBJECT_TEACHER_ID   INT4                 null,
   STUDENT_ID           INT4                 null,
   DATE                 DATE                 null,
   constraint PK_STUDENT_SUBJECT primary key (STUDENT_SUBJECT_ID)
);

/*==============================================================*/
/* Table: SUBJECT                                               */
/*==============================================================*/
create table SUBJECT (
   SUBJECT_ID           SERIAL               not null,
   NAME                 VARCHAR(250)         null,
   SEMESTER             INT4                 null,
   CREDITS              INT4                 null,
   STATUS               INT2                 null,
   constraint PK_SUBJECT primary key (SUBJECT_ID)
);

/*==============================================================*/
/* Table: SUBJECT_TEACHER                                       */
/*==============================================================*/
create table SUBJECT_TEACHER (
   SUBJECT_TEACHER_ID   SERIAL               not null,
   SUBJECT_ID           INT4                 null,
   TEACHER_ID           INT4                 null,
   DATE                 DATE                 null,
   constraint PK_SUBJECT_TEACHER primary key (SUBJECT_TEACHER_ID)
);

/*==============================================================*/
/* Table: TEACHER                                               */
/*==============================================================*/
create table TEACHER (
   TEACHER_ID           SERIAL               not null,
   NAME                 VARCHAR(250)         null,
   EMAIL                VARCHAR(250)         null,
   PHONE                VARCHAR(50)          null,
   constraint PK_TEACHER primary key (TEACHER_ID)
);

alter table STUDENT_SUBJECT
   add constraint FK_STUDENT__REFERENCE_SUBJECT foreign key (SUBJECT_ID)
      references SUBJECT (SUBJECT_ID)
      on delete restrict on update restrict;

alter table STUDENT_SUBJECT
   add constraint FK_STUDENT__REFERENCE_SUBJECT_ foreign key (SUBJECT_TEACHER_ID)
      references SUBJECT_TEACHER (SUBJECT_TEACHER_ID)
      on delete restrict on update restrict;

alter table STUDENT_SUBJECT
   add constraint FK_STUDENT__REFERENCE_STUDENT foreign key (STUDENT_ID)
      references STUDENT (STUDENT_ID)
      on delete restrict on update restrict;

alter table SUBJECT_TEACHER
   add constraint FK_SUBJECT__REFERENCE_SUBJECT foreign key (SUBJECT_ID)
      references SUBJECT (SUBJECT_ID)
      on delete restrict on update restrict;

alter table SUBJECT_TEACHER
   add constraint FK_SUBJECT__REFERENCE_TEACHER foreign key (TEACHER_ID)
      references TEACHER (TEACHER_ID)
      on delete restrict on update restrict;

