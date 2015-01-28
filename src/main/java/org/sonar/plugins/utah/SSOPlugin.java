/*
 * Sonar OpenID Plugin
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

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.sonar.api.ExtensionProvider;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.ServerExtension;
import org.sonar.api.SonarPlugin;
import org.sonar.api.config.Settings;

import java.util.List;


@Properties({
    @Property(key = "reverseproxyauth.header.name", name = "Header Name", defaultValue = "X-Forwarded-User"),
    @Property(key = "reverseproxyauth.localhost", name = "Hostname to allow Sonar executions", defaultValue = "localhost"),
    @Property(key = "reverseproxyauth.header.fullname", name = "Header Full Name", defaultValue = "fullName"),
    @Property(key = "reverseproxyauth.header.groups", name = "Header Groups/Roles", defaultValue = "groups"),
    @Property(key = "reverseproxyauth.header.email", name = "Header E-mail", defaultValue = "email") })
public final class SSOPlugin extends SonarPlugin {

  public List getExtensions() {
    return ImmutableList.of(Extensions.class);
  }
  public static final class Extensions extends ExtensionProvider implements ServerExtension {
    private Settings settings;

    public Extensions(Settings settings) {
      this.settings = settings;
    }

    @Override
    public Object provide() {
      List<Class> extensions = Lists.newArrayList();
      if (isRealmEnabled()) {
        Preconditions.checkState(settings.getBoolean("sonar.authenticator.createUsers"), "Property sonar.authenticator.createUsers must be set to true.");
        extensions.add(SSOSecurityRealm.class);
        extensions.add(SSOAuthenticator.class);
        extensions.add(SSOValidationFilter.class);
        extensions.add(SSOAuthenticationFilter.class);
      }
      return extensions;
    }

    private boolean isRealmEnabled() {
      return SSOSecurityRealm.KEY.equalsIgnoreCase(settings.getString("sonar.security.realm"));
    }
  }
}
