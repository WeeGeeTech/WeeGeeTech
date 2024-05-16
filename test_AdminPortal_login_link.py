from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

# Test Number Sel_Spr4.6

# This tests "Login or Create Account" button from the Admin Portal
# It verifies whether the link takes the user to the correct location.
# Note: user login credentials must be for an Admin user for this to work properly
# Standard users do not have access to AdminPortal.html thus test will fail if user is not Admin

# Initialize the WebDriver
driver = webdriver.Chrome()  # Change this to the path of your WebDriver if needed

try:
    # Open the website
    driver.get("https://main.d2oouagfij5oad.amplifyapp.com/HomePage")

    # Find the login form elements
    username_input = driver.find_element(By.ID, "username")
    password_input = driver.find_element(By.ID, "password")
    login_button = driver.find_element(By.XPATH, "//form[@id='loginForm']//button[@type='submit']")

    # Input credentials
    username_input.send_keys("uuuuuu") # place username in quotes
    password_input.send_keys("pppppp") # place password in quotes

    # Click the login button
    login_button.click()

    # Wait 5 secs for login process to complete
    time.sleep(5)

    # Verify if redirected to the correct page after successful login
    if driver.current_url == "https://main.d2oouagfij5oad.amplifyapp.com/HomePage.html":
        print("Login successful!")
    else:
        print("Login failed!")

    # Find and click the 'admin portal' button
    logout_link = driver.find_element(By.ID, "admin-portal-link")
    logout_link.click()

    if driver.current_url == "https://main.d2oouagfij5oad.amplifyapp.com/AdminPortal.html":
        print("Admin Portal accessed!")
    else:
        print("Admin Portal access failed!")

    # Reset driver to current page so that ID can be located on this page.
    driver.get("https://main.d2oouagfij5oad.amplifyapp.com/AdminPortal.html")

    print("driver current URL is... " + driver.current_url)
    # Wait for page to load
    time.sleep(5)

    # iframe_one = driver.find_element(By.ID, "iframe-one")

    # Click on the login-portal-link inside iframe-one
    login_portal_link = driver.find_element(By.ID, "login-portal-link")
    login_portal_link.click()

    # Wait for page to load
    time.sleep(5)

    # Verify if redirected to the proper page after clicking button
    if driver.current_url == "https://main.d2oouagfij5oad.amplifyapp.com/index.html":
        print("Book Stacks Admininstrative portal successfully opened 'Login or Create Account'!")
    else:
        print("Book Stacks Admininstrative portal failed to open 'Login or Create Account'!")

except Exception as e:
    print("An error occurred:", e)

finally:
    # Close the browser window
    driver.quit()
