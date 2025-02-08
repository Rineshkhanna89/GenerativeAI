const { test, expect } = require('@playwright/test');

test('login-page-test', async ({ page }) => {
  const USERNAME = "DemoSalesManager";
  const PASSWORD = "crmsfa";
  const LOGIN_URL = "http://leaftaps.com/opentaps/control/main";

  await page.goto(LOGIN_URL);

  // Fill username
  await page.fill('id=username', USERNAME);

  // Fill password
  await page.fill('id=password', PASSWORD);

  // Click login button
  await page.click('text="Login"');

  // Verify logout button is visible
  await expect(page.getByText('Logout')).toBeVisible();
});