import unittest
import time
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By

cd_path = "/opt/homebrew/bin/chromedriver"

# class and first method not part of original code
class TestWebsiteSearch(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()

    # Test number D2
    # tests UPC 363004972316 search against proper result 'Helicopter Pilot Reports'
    def test_search_results_1(self):
        this_search = "363004972316" # what is entered in search field
        expected_results = "Helicopter Pilot Reports"
        # Open Google in the Chrome browser
        self.driver.get("https://main.d2oouagfij5oad.amplifyapp.com/HomePage")
        # Wait for a few seconds
        time.sleep(7)  # input is number of seconds to wait 
        # Find the search bar by its ID
        search_bar = self.driver.find_element(By.ID, "searchBar")
        # Enter text into the search bar
        search_bar.send_keys(this_search)
        # Press return key to perform the search
        search_bar.send_keys(Keys.RETURN)
        # Wait for a few seconds
        time.sleep(8) 
        # Find the element containing the search results
        search_results = self.driver.find_element(By.ID, "modalResults")
        actual_results = search_results.text
        self.assertEqual(actual_results, expected_results, "Search results do not match expected")
    
    # Test number D3    
    # tests "Helicopter Pilot Reports" search against proper result 'Helicopter Pilot Reports'
    def test_search_results_2(self):
        this_search = "Helicopter Pilot Reports" # what is entered in search field
        expected_results = "Helicopter Pilot Reports"
        # Open Google in the Chrome browser
        self.driver.get("https://main.d2oouagfij5oad.amplifyapp.com/HomePage")
        # Wait for a few seconds
        time.sleep(7)  # input is number of seconds to wait 
        # Find the search bar by its ID
        search_bar = self.driver.find_element(By.ID, "searchBar")
        # Enter text into the search bar
        search_bar.send_keys(this_search)
        # Press return key to perform the search
        search_bar.send_keys(Keys.RETURN)
        # Wait for a few seconds
        time.sleep(10) 
        # Find the element containing the search results
        search_results = self.driver.find_element(By.ID, "modalResults")
        actual_results = search_results.text
        self.assertEqual(actual_results, expected_results, "Search results do not match expected")
         
    def tearDown(self):
        # Quit the WebDriver instance
        self.driver.quit()

if __name__ == "__main__":
    unittest.main()

