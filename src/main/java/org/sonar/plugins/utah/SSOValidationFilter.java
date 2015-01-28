/*
 * Sonar SSO Plugin
 * Copyright (C) 2012 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.plugins.utah;

import com.google.common.annotations.VisibleForTesting;
import org.sonar.api.web.ServletFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Validate tokens forwarded by the SSO provider after the request initiated by {@link SSOAuthenticationFilter}.
 * If authentication is successful, then object of type UserDetails is added to request attributes.
 */
public final class SSOValidationFilter extends ServletFilter {

  static final String USER_ATTRIBUTE = "SSO_user";
//  private  ;

  public SSOValidationFilter( ) {
//    this. = ;
  }

  @Override
  public UrlPattern doGetPattern() {
    return UrlPattern.create("/SSO/validate");
  }

  public void init(FilterConfig filterConfig) throws ServletException {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//    ParameterList responseParameters = new ParameterList(request.getParameterMap());
//    HttpServletRequest httpRequest = (HttpServletRequest) request;
//    HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//    String receivingURL = requestUrl(httpRequest);
//    UserDetails user;
//    try {
//      user = .verify(receivingURL, responseParameters);
//    } catch (RuntimeException e) {
//      LoggerFactory.getLogger(SSOValidationFilter.class).error("Fail to verify SSO request", e);
//      throw e;
//    }
//    if (user == null) {
//      httpResponse.sendRedirect("/SSO/unauthorized");
//    } else {
//      request.setAttribute(USER_ATTRIBUTE, user);
//      filterChain.doFilter(request, response);
//    }
  }

  @VisibleForTesting
  String requestUrl(HttpServletRequest httpRequest) {
//    StringBuilder receivingURL = new StringBuilder(.getReturnToUrl());
//    String queryString = httpRequest.getQueryString();
//    if (StringUtils.isNotEmpty(queryString)) {
//      // the return-to url does not contain ? (see #initReturnToUrl()
//      receivingURL.append("?").append(queryString);
//    }
//    return receivingURL.toString();
	  return "";
  }

  public void destroy() {
  }


}