from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

# Test Number Sel_Spr4.5

# This tests "Add or Edit Inventory" link from the Admin Portal
# It verifies whether the link takes the user to the correct location.
# Note: user login credentials must be for an Admin user for this to work properly
# Standard users do not have access to AdminPortal.html, thus test will fail if user is not Admin

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

    # Click on the 'Add Edit Inventory' Link
    login_portal_link = driver.find_element(By.ID, "add-edit-inv-link")
    login_portal_link.click()

    # Wait for page to load
    time.sleep(5)

    # Verify if redirected to the proper page after clicking button
    if driver.current_url == "https://main.d2oouagfij5oad.amplifyapp.com/addBooks.html":
        print("Book Stacks Admininstrative portal successfully opened 'Add or Edit Inventory'!")
    else:
        print("Book Stacks Admininstrative portal failed to open 'Add or Edit Inventory'!")

except Exception as e:
    print("An error occurred:", e)

finally:
    # Close the browser window
    driver.quit()
