# -*- coding: utf-8 -*-
#超神影业资讯爬取
import urllib.request
import requests
from bs4 import BeautifulSoup
import re
import pymysql

headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36',
        'Accept-Encoding': 'ggzip, deflate',
        'Accept-Language': 'zh-CN,zh;q=0.9'
    }
#获得资讯
def getNews(url,cnum):
	payload = {
            'g': 'Home',
            'm': 'Ajax',
            'a':'news_list',
            'num':5,
			'catid': 12,
			'cnum': cnum,
			'lang': 1
	}
	response = requests.get(url, headers=headers, params=payload)
	news = response.json()
	return news
#执行sql语句
def executeSql(sql,values):
    conn = pymysql.connect(host='127.0.0.1',user='root',passwd='123456',db='cartoon',charset='utf8')
    cursor = conn.cursor()
    conn.set_charset('utf8')
    try:   
        effect_row = cursor.execute(sql, values)
        # 提交，不然无法保存新建或者修改的数据
        conn.commit()
        # 关闭游标
        cursor.close()
        print("数据插入成功")
    except Exception:
        print("插入数据异常",Exception)  
        conn.rollback() 
    finally:
        # 关闭连接
        conn.close()

#插入新闻资讯
def insertNews(url):
	i=0
	while i<10:
		news=getNews(url,i)
		for new in news:
			newsObjs = [new.get('title'), '默认','超神影业','http://www.chaoshenyl.com'+new.get('url')]
			sql = "insert into infomation(title,status,company,url)" \
                "values(%s,%s,%s,%s) "
			executeSql(sql=sql,values=newsObjs)
		i=i+1
		
# 要爬取的网页链接
url= 'http://www.chaoshenyl.com/index.php?'
insertNews(url)

