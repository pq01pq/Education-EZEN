import selenium
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time

path = 'C:/Python/EZEN/tool/chromedriver.exe'
driver = webdriver.Chrome(path)

driver.get('http://www.naver.com')

# element = driver.find_element_by_id('yna_rolling')
# print(element.tag_name)
# print(element.text)
'''
element.click()
time.sleep(1)
element = driver.find_element_by_class_name('on')
element.click()
'''
'''
element = driver.find_element_by_id('query')
element.send_keys('삼성전자' + Keys.ENTER)
'''
'''
driver.find_element_by_class_name('link_login').click()
time.sleep(1)
id_tag = driver.find_element_by_id('id')
id_tag.send_keys('pq01pq')

pw_tag = driver.find_element_by_id('pw')
pw_tag.send_keys('dizzy2358@@')

driver.find_element_by_id('log.login').click()
'''

# js로 실행
driver.execute_script('document.querySelector("a.link_login").click()')
time.sleep(1)
driver.execute_script('document.querySelector("input#id").value = "pq01pq"')
driver.execute_script('document.querySelector("input#pw").value = "dizzy2358@@"')
driver.execute_script('document.querySelector("button.btn_login").click()')
