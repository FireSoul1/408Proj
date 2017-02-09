package com.stressmanager;

// @formatter:off
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;



// import lombok.EqualsAndHashCode;
// import lombok.Getter;
// import lombok.Setter;
// import lombok.ToString;

// @EqualsAndHashCode(of = { "username", "token"})
// @ToString(of = { "id", "username" })
// @Setter
// @Getter
@Entity
@Table(name = "users")
public class AuthUser {

	static final int MAX_LENGTH_USERNAME = 30;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true, length = MAX_LENGTH_USERNAME)
	private String username;

	@Column(nullable = false)
	private String password;

	private boolean enabled;
	private LocalDateTime creationTime;
	private LocalDateTime modificationTime;

	// @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// private Set<Role> roles = new HashSet<Role>();

	public AuthUser() {
	}

	/**
	 * Constructor used exclusively by {@link CustomUserDetails}}
	 *
	 * @param user
	 */
	public AuthUser(final AuthUser user) {
		this.id = user.id;
		this.username = user.username;
		this.password = user.password;
		this.enabled = user.enabled;
	}

	@PrePersist
	public void prePersist() {
		creationTime = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		modificationTime = LocalDateTime.now();
	}

}
