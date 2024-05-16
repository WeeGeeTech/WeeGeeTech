from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

# Test Number Sel_Spr4.7

# This tests "Admin Portal" button from the hamburger menu on the Book Stacks Home Page
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

    # Find and click the 'Admin Portal' button on the page
    logout_link = driver.find_element(By.ID, "admin-portal-link")
    logout_link.click()

    # Wait for logout process to complete
    time.sleep(5)

    # Verify if redirected to the index (login) page after logout
    if driver.current_url == "https://main.d2oouagfij5oad.amplifyapp.com/AdminPortal.html":
        print("Book Stacks Admin Portal button on HomePage.html successfully opened the admininstrative portal!")
    else:
        print("Admin Portal button on HomePage.html menu failed to open admin portal!")

except Exception as e:
    print("An error occurred:", e)

finally:
    # Close the browser window
    driver.quit()
