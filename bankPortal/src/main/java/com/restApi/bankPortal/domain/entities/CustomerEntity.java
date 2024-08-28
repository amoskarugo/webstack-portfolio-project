package com.restApi.bankPortal.domain.entities;



import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.restApi.bankPortal.enums.Role;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Configuration
@Table(name = "customers")
public class CustomerEntity implements UserDetails {
    @Id
    private Long customer_id;
    @Column(nullable = false)
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDate dob;
    private LocalDateTime created_at;
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(fetch = FetchType.EAGER,mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts;

    @PreRemove
    public void clearAccounts() {
        for (Account account : accounts) {
            account.setCustomer(null);
        }
        accounts.clear();
    }



    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}