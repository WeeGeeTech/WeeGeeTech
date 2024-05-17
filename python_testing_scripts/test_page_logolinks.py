import unittest
import time
from selenium import webdriver
from selenium.webdriver.common.by import By

# Test number S3_1
# This test checks the 'welcome' page to see if the logo in the corner links to the home page.
class TestWebpage(unittest.TestCase):
    def setUp(self):
        # Initialize Chrome WebDriver
        self.driver = webdriver.Chrome()
        
    def test_open_webpage(self):
        # Opens the 'index' or 'welcome' webpage (before logging in)
        self.driver.get("https://main.d2oouagfij5oad.amplifyapp.com/")
        # time.sleep(5)

        # Verify that the logo has a hyperlink
        logo_element = self.driver.find_element(By.ID, "logo_img")
        parent_element = logo_element.find_element(By.XPATH, "..")

        if parent_element.tag_name == "a":
            print("The index/welcome page logo has a hyperlink")
        else:
            print("The index/welcome page logo does not have a hyperlink")
        
    def tearDown(self):
        # Quit the WebDriver
        self.driver.quit()

if __name__ == "__main__":
    unittest.main()
