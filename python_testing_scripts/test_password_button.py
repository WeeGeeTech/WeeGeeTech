from selenium import webdriver 
from selenium.webdriver.common.keys import Keys 
from selenium.webdriver.common.by import By 
from selenium.webdriver.support.ui import WebDriverWait 
from selenium.webdriver.support import expected_conditions as EC
import time

# Test Number Sel_Spr4.8

# This tests 'password' button from the hamburger menu on the Book Stacks Home Page

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

  # Find and click the hamburger menu button to expand the menu
    hamburger_menu_button = driver.find_element(By.CLASS_NAME, "navbar-toggler")
    hamburger_menu_button.click()

    # Wait for the dropdown menu to appear
    WebDriverWait(driver, 10).until(EC.visibility_of_element_located((By.XPATH, "//ul[@class='navbar-nav']")))

    # Find and click the 'password' link within the dropdown menu
    logout_link = driver.find_element(By.ID, "passwordLink")
    logout_link.click()

    # Wait for logout process to complete
    time.sleep(5)

    # Verify if redirected to the index (login) page after logout
    if driver.current_url == "https://bookstackslogin.auth.us-east-1.amazoncognito.com/forgotPassword?client_id=67vbvpg87mt1fptbkku3iigi38&response_type=code&scope=email+openid+phone&redirect_uri=https%3A%2F%2Fmain.d2oouagfij5oad.amplifyapp.com%2FHomePage":
        print("Password Reset successfully accessed via Home hamburger menu Password link!")
    else:
        print("Password link in Home hamburger menu failed!")

except Exception as e:
    print("An error occurred:", e)

finally:
    # Close the browser window
    driver.quit()
