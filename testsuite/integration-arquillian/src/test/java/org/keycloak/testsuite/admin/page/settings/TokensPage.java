/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.testsuite.admin.page.settings;

import java.util.concurrent.TimeUnit;
import org.keycloak.testsuite.admin.page.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static java.lang.String.valueOf;
import static org.keycloak.testsuite.admin.util.SeleniumUtils.waitGuiForElement;
import static org.apache.commons.lang3.text.WordUtils.capitalize;

/**
 *
 * @author Petr Mensik
 */
public class TokensPage extends AbstractPage {
	
	@FindBy(id = "ssoSessionIdleTimeout")
	private WebElement sessionTimeout;
	
	@FindBy(name = "ssoSessionIdleTimeoutUnit")
	private Select sessionTimeoutUnit;
	
	@FindBy(id = "ssoSessionMaxLifespan")
	private WebElement sessionLifespanTimeout;
	
	@FindBy(name = "ssoSessionMaxLifespanUnit")
	private Select sessionLifespanTimeoutUnit;

	public void setSessionTimeout(int timeout, TimeUnit unit) {
		setTimeout(sessionTimeoutUnit, sessionTimeout, timeout, unit);
	}
	
	public void setSessionTimeoutLifespan(int time, TimeUnit unit) {
		setTimeout(sessionLifespanTimeoutUnit, sessionLifespanTimeout, time, unit);
	}
	
	private void setTimeout(Select timeoutElement, WebElement unitElement,
			int timeout, TimeUnit unit) {
		waitGuiForElement(sessionTimeout);
		timeoutElement.selectByValue(capitalize(unit.name().toLowerCase()));
		unitElement.clear();
		unitElement.sendKeys(valueOf(timeout));
		primaryButton.click();
	}
}
