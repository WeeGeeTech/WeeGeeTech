import unittest
from selenium import webdriver

# Test number D1
# This tests the Home Page to see if it has the expected title property in HTML
class TestWebpage(unittest.TestCase):
    def setUp(self):
        # Initialize Chrome WebDriver
        self.driver = webdriver.Chrome()
        
    def test_open_webpage(self):
        # Open the webpage
        self.driver.get("https://main.d2oouagfij5oad.amplifyapp.com/HomePage")
        
        # Verify that the webpage title is as expected
        expected_title = "Welcome to Book Stacks"
        actual_title = self.driver.title
        self.assertEqual(actual_title, expected_title, "Webpage title is not as expected")
        
    def tearDown(self):
        # Quit the WebDriver
        self.driver.quit()

if __name__ == "__main__":
    unittest.main()
