package penguins.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static penguins.backend.User.UserType.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http


                .csrf().disable()
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests((requests)->requests
                .requestMatchers("/api/Authentication/**")

                .permitAll()

                .requestMatchers("/admin/homePage").hasRole(ADMIN.name())
                .requestMatchers("/admin/addCourse").hasRole(ADMIN.name())
                .requestMatchers("/admin/manageSemester").hasRole(ADMIN.name())

                .requestMatchers("/api/admin/{user_id}/**").hasRole(ADMIN.name())

                /*.requestMatchers("/api/admin/{user_id}/").hasRole(ADMIN.name())
                .requestMatchers("/api/admin/{user_id}/courses").hasRole(ADMIN.name())
                .requestMatchers("/api/admin/{user_id}/courses/add").hasRole(ADMIN.name())
                .requestMatchers("/api/admin/{user_id}/courses/remove").hasRole(ADMIN.name())
                .requestMatchers("/api/admin/{user_id}/semester/start").hasRole(ADMIN.name())
                .requestMatchers("/api/admin/{user_id}/semester/finish").hasRole(ADMIN.name())
                .requestMatchers("/api/admin/{user_id}/evaluation/start").hasRole(ADMIN.name())
                .requestMatchers("/api/admin/{user_id}/evaluation/finish").hasRole(ADMIN.name())
                .requestMatchers("/api/admin/{user_id}/add-or-drop/start").hasRole(ADMIN.name())
                .requestMatchers("/api/admin/{user_id}/add-or-drop/finish").hasRole(ADMIN.name())
                .requestMatchers("/api/admin/{user_id}/semester").hasRole(ADMIN.name())
                .requestMatchers("/api/admin/{user_id}/update-info").hasRole(ADMIN.name())
                .requestMatchers("/api/admin/{user_id}/admit/{student_id}").hasRole(ADMIN.name())
                .requestMatchers("/api/admin/{user_id}/create-first-admin").hasRole(ADMIN.name())
                .requestMatchers("/api/admin/{user_id}/add-examples").hasRole(ADMIN.name())*/

                .requestMatchers("/student/homePage").hasRole(STUDENT.name())
                .requestMatchers("/student/enrollCourses").hasRole(STUDENT.name())
                .requestMatchers("/api/students/{userId}/**").hasRole(STUDENT.name())

                .requestMatchers("/api/instructors/{userId}").hasRole(INSTRUCTOR.name())

                .requestMatchers("/api/department-managers/{userId}").hasRole(DEPARTMENT_MANAGER.name())
                                .requestMatchers("/css/**","/js/**").permitAll().anyRequest().permitAll()

//                .anyRequest()
//                .authenticated()
                        )
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()

                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/homepage", true)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()


                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/Authentication/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext()))
                ;


    return http.build();
    }
}
