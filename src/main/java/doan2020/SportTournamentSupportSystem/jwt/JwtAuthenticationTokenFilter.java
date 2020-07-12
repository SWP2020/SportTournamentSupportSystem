
package doan2020.SportTournamentSupportSystem.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import doan2020.SportTournamentSupportSystem.service.impl.JwtService;
import doan2020.SportTournamentSupportSystem.service.impl.UserDetailsServiceImpl;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	private final static String TOKEN_HEADER = "Authorization";

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserDetailsServiceImpl userService;
	
//	@Autowired
//	private IUserService userService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(TOKEN_HEADER);

		if (jwtService.validateTokenLogin(authToken)) {
			String username = jwtService.getUsernameFromToken(authToken);
//			String username = "DoVanCong";
			UserDetails userDetails = userService.loadUserByUsername(username);
//			UserEntity user = userService.loadUserByUsername(username);
			System.out.println(username);
			if (userDetails != null) {
//				boolean enabled = true;
//				boolean accountNonExpired = true;
//				boolean credentialsNonExpired = true;
//				boolean accountNonLocked = true;
//				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//				authorities.add(new SimpleGrantedAuthority("Admin"));
//				authorities.add(new SimpleGrantedAuthority("User"));
//				UserDetails userDetails = new User(username, user.getPassword(), enabled, accountNonExpired,
//						credentialsNonExpired, accountNonLocked, user.getAuthorities());
				System.out.println(userDetails.getAuthorities().toString());

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				System.out.println(userDetails.getAuthorities().toString());
				System.out.println(username+ "2");
			}
		}

		chain.doFilter(request, response);
	}
}