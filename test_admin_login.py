from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
import time

# This tests the login credentials for an ADMIN USER on the Book Stacks site

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

    # Wait for login process to complete
    time.sleep(5)

# Verify if the "You are in Admin View" message is displayed
    admin_view_notice = driver.find_element(By.ID, "user-is-admin")
    if admin_view_notice.is_displayed():
        print("Admin login successful! 'You are in Admin View' message is displayed.")
    else:
        print("Admin login failed! 'You are in Admin View' message is not displayed.")

except Exception as e:
    print("An error occurred:", e)

finally:
    # Close the browser window
    driver.quit()
