package rest.storage.api.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.oauth.*;
import com.google.appengine.api.users.User;

public class OAuthFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession(false);
		
		User user = null;
		OAuthService oauth = OAuthServiceFactory.getOAuthService();
        try {
            
            user = oauth.getCurrentUser();
            
            
            //session.setAttribute("user", user);
            
            // Attach some oauth data to the header
            response.addHeader("oauth-email", user.getEmail());
            response.addHeader("oauth-name", user.getNickname());
            
            chain.doFilter(req, resp);

        } catch (OAuthRequestException e) {
            // The consumer made an invalid OAuth request, used an access token that was
            // revoked, or did not provide OAuth information.
            // ...
        	boolean t = true;
        	try {
				System.out.println("OAuthConsumerKey: " + oauth.getOAuthConsumerKey());
			} catch (OAuthRequestException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	t = false;
        	//response.sendRedirect("/auth.html");
        	response.sendError(401, "OAuth 2.0 Authentication required");
        }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

}
