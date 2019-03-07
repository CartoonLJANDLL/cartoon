# -*- coding: utf-8 -*-
#若森数字资讯爬取
import urllib.request
from bs4 import BeautifulSoup
from datetime import datetime
import re
import pymysql

#自定义一个函数拿到解析后的指定网站的数据
def getHtml (url):
    #定义一个headers,存储刚才复制下来的报头,模拟成浏览器
    headers = ('User-Agent',
               "Mozilla/5.0 (Windows NT 10.0; Win32; x32; rv:48.0) Gucko/20130101 Firefox/58.0")
    opener = urllib.request.build_opener()
    opener.addheaders = [headers]
    # 将opener安装为全局
    urllib.request.install_opener(opener)
    html = urllib.request.urlopen(url).read().decode('utf-8', 'ignore')
    bs = BeautifulSoup(html,'html.parser')
    return bs
#获得资讯标题和超链接所在a标签
def getTitle(url):
	bs=getHtml (url)
    # 用beautifulsoup的select,找到所有超链接的<h3>标签
	titles = bs.select('.list-con > li > a')
	return titles

#获得资讯发布时间
def getTime(url):
	bs=getHtml (url)
    # 用beautifulsoup的select,找到所有div(class=text_1)下的第一个<span>标签
	times = bs.select('.list-con > li > span')
	return times
	
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
	titlelist = getTitle(url)
	timelist=getTime(url)
	for title,time in zip(titlelist,timelist):
		#print(newsObj.get('title'))
		newsObjs = [title.text, '默认',time.text,'若森数字',title.get('href')]
		sql = "insert into infomation(title,status,time,company,url)" \
                "values(%s,%s,%s,%s,%s) "
		executeSql(sql=sql,values=newsObjs)
		
# 要爬取的网页链接
pages=[]
pages.append('http://www.rocen.com.cn/news/index.html')
pages.append('http://www.rocen.com.cn/news/list_2_2.html')
pages.append('http://www.rocen.com.cn/news/list_2_3.html')
for page in pages:
    insertNews(page)