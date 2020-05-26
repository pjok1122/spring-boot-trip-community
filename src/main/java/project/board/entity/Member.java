package project.board.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import project.board.enums.MemberRole;
import project.board.util.Sha256Utils;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(name="EMAIL_UNIQUE", columnNames = {"email"}) })
public class Member extends BaseTimeEntity{
	
	@Id @GeneratedValue
	private Long id;
	
	private String email;
	private String password;
	private String salt;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd hh:mm:ss")
	private LocalDateTime loginDate;
	
	@Enumerated(EnumType.STRING)
	@Default
	private MemberRole role = MemberRole.USER;
	
	public void updateLoginDate() {
		loginDate = LocalDateTime.now();
	}

	public static Member createMember(String email, String password) {
		Member member = new Member();
		member.email = email;
		member.salt = UUID.randomUUID().toString();
		member.password = Sha256Utils.sha256(password, member.getSalt());
		return member;
	}
	
}
