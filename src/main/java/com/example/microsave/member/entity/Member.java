package com.example.microsave.member.entity;
import com.example.microsave.group.entity.Group;
import jakarta.persistence.*; import java.time.LocalDate;
@Entity @Table(name="members") public class Member {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=100) private String name; @Column(length=20) private String phone; @Column(length=300) private String address;
 @Column(nullable=false) private LocalDate joinDate; @Column(nullable=false,length=20) private String status;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="group_id",nullable=false) private Group group;
 public Member(){} public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;} public String getPhone(){return phone;} public void setPhone(String v){phone=v;} public String getAddress(){return address;} public void setAddress(String v){address=v;} public LocalDate getJoinDate(){return joinDate;} public void setJoinDate(LocalDate v){joinDate=v;} public String getStatus(){return status;} public void setStatus(String v){status=v;} public Group getGroup(){return group;} public void setGroup(Group v){group=v;}
}
