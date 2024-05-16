from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
import time

# This tests the 'TOP' scroll button on the home page of the Book Stacks site

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

     # Scroll down the page
    driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")

    # Wait for the scroll action to complete
    time.sleep(2)  

    # Locate and click the "top" button
    top_button = driver.find_element(By.ID, "myBtn")
    top_button.click()

    # Wait for the page to scroll to the top
    time.sleep(2)  

    # Verify that the page has scrolled to the top
    if driver.execute_script("return window.scrollY;") == 0:
        print("Page scrolled to the top successfully!")
    else:
        print("Page scroll to top failed!")

except Exception as e:
    print("An error occurred:", e)

finally:
    # Close the browser window
    driver.quit()
