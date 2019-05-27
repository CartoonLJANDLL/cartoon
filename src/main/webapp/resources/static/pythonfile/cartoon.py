# -*- coding: utf-8 -*-
# 超神影业资讯爬取
import urllib.request
import requests
from bs4 import BeautifulSoup
import re
import pymysql
from pyquery import  PyQuery as pq
import numpy as np
import os
from PIL import Image
from io import BytesIO
import time
# headers = {
#     'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36',
#     'Accept-Encoding': 'ggzip, deflate',
#     'Accept-Language': 'zh-CN,zh;q=0.9'
# }

# 获得动漫html
def getOperaHtml(url):
    try:
        print('正在请求目标网页....',url)
        html = pq(url, encoding = 'utf-8')
        return html
    except:
        print('请求目标网页失败，请检查错误重试')
        return None

#通过更新至字段含有全字判断status值
def getStatus(updateto):
    if (updateto.find('全') != -1):
       status = 1
    else:
        status = 0
    return status
#清洗optype数据，去掉不必要的部分
def getWashedOptype(optype):
    optype = optype.replace('类型：', '')
    optype = optype.replace('番剧 /', '')
    optype = optype.replace('番剧','')
    optype = optype.replace('内地 ','')
    optype = optype.replace('中国 / ','')
    optype = re.sub(r'\b\d+(?:\.\d+)?\s+', '', optype)
    # optype.replace('')
    # optype.replace('')
    # optype.replace('')
    # optype.replace('')
    return optype
#解析腾讯视频
def getTXOpera(turl):
    html =getOperaHtml(turl)
    lists = html('.list_item')
    aurls = []
    imgurls = []
    cartnames = []
    name_descs = []
    updatetos = []
    optypes = []
    statuses = []
    lists.find('i[class = "mark_v"]').remove()
    for list in lists.items():
        a = list.find('a[class = "figure"]')
        img = list('.figure img')
        aurl = a.attr.href
        respon = getOperaHtml(aurl)
        optype = respon('.video_tags._video_tags a').text()
        optype = getWashedOptype(optype)
        imgurl = 'http:' + img.attr('r-lazyload')
        #print("FIMG" + fimgurl)
        #imgurl = getTImgs(fimgurl)
        updateto = list('.figure_caption').text()
        status = getStatus(updateto)
        name_desc = list('.figure_desc').text()
        cartname = img.attr.alt
        statuses.append(status)
        aurls.append(aurl)
        imgurls.append(imgurl)
        updatetos.append(updateto)
        name_descs.append(name_desc)
        cartnames.append(cartname)
        optypes.append(optype)
    createSql(cartnames, imgurls, aurls, name_descs, updatetos, optypes, statuses)

#解析爱奇艺视频
def getIQiOpera(iurl):
    html = getOperaHtml(iurl)
    lists = html('.site-piclist.site-piclist-180236.site-piclist-auto li')
    aurls = []
    imgurls = []
    cartnames = []
    name_descs = []
    updatetos = []
    optypes = []
    statuses = []
    for list in lists.items():
        a = list('.site-piclist_pic_link')
        img = list('.site-piclist_pic_link img')
        aurl = a.attr.href
        ahtml = getOperaHtml(aurl)
        optype = ahtml('.episodeIntro-type').text()
        optype = getWashedOptype(optype)
        imgurl = 'http:' + img.attr.src
        #imgurl = getImgs(fimgurl)
        updateto = list('.site-piclist_pic_link p').text()
        status = getStatus(updateto)
        print("Status")
        print(updateto)
        print(status)
        name_desc = list('.role_info').text()
        cartname = img.attr.alt
        statuses.append(status)
        optypes.append(optype)
        aurls.append(aurl)
        imgurls.append(imgurl)
        updatetos.append(updateto)
        name_descs.append(name_desc)
        cartnames.append(cartname)
    createSql(cartnames, imgurls, aurls, name_descs, updatetos, optypes, statuses)

#解析优酷视频
def getYouKuOpera(uurl):
    html = getOperaHtml(uurl)
    lists = html('.yk-col4.mr1')
    lists.find('li[class = "actor"]').remove()
    lists.find('li[class = "title"]').remove()
    aurls = []
    imgurls = []
    cartnames = []
    optypes = []
    #name_descs = []
    updatetos = []
    statuses = []
    iframeurls = []

    for list in lists.items():
        a = list('.p-thumb a')
        img = list('.quic')
        aurl = 'https:' + a.attr.href
        burl = aurl.split('id_')[-1]
        rl = burl.split('.html')[0]
        iframeurl = 'http://player.youku.com/embed/' + rl
        imgurl = img.attr.src
        #imgurl = getImgs(fimgurl)
        updateto = list('.p-time span').text()
        status = getStatus(updateto)
        optype = list('.info-list li').text()
        optype = getWashedOptype(optype)
        #name_desc = list('.role_info').text()
        cartname = img.attr.alt
        statuses.append(status)
        aurls.append(aurl)
        imgurls.append(imgurl)
        updatetos.append(updateto)
        optypes.append(optype)
        #name_descs.append(name_desc)
        cartnames.append(cartname)
        iframeurls.append(iframeurl)
    i = 0
    while i < len(aurls):
        val = (cartnames[i], imgurls[i], aurls[i], updatetos[i], optypes[i], statuses[i], iframeurls[i], cartnames[i])
        print(val)
        sql = "INSERT INTO opera(op_name,op_photourl,op_url,op_updateto,op_type,op_status,op_iframeurl,op_time) " \
              "SELECT %s,%s,%s,%s,%s,%s,%s,NOW() "\
              "from DUAL WHERE NOT EXISTS(SELECT op_name FROM opera WHERE op_name = %s)"
        if executeSql(sql=sql, values=val) == 0:
            sql = "UPDATE opera " \
                  "SET op_updateto =%s, op_url = %s, op_status = %s, op_photourl = %s, op_iframeurl = %s, op_time = now() " \
                  "WHERE op_name = %s"
            val = (updatetos[i], aurls[i], statuses[i], imgurls[i], iframeurls[i], cartnames[i])
            print(sql)
            executeSql(sql=sql, values=val)
        i = i + 1
        print(i)

#爬取腾讯Opera图片存储到本地
def getTImgs(imgurl):
    root = "G://Jsp//eclipse//myeclipse//guomanwang//src//main//webapp//resources//oppics//"
    path = root + imgurl.split('/')[-2]
    print("PATH" + path)
    try:
        if not os.path.exists(root):
            os.makedirs(root)
        if not os.path.exists(path):
            r = requests.get(imgurl)
            with open(path, 'wb') as f:
                f.write(r.content)
                f.close()
                print('图片保存成功')
                return path
        else:
            print('图片已存在，保存失败')
            return path
    except:
        print("爬取失败")
    #time.sleep(1)

#爬取Opera图片存储到本地
def getImgs(imgurl):
    root = "G://Jsp//eclipse//myeclipse//guomanwang//src//main//webapp//resources//oppics//"
    path = root + imgurl.split('/')[-1]
    print("PATH" + path)
    try:
        if not os.path.exists(root):
            os.makedirs(root)
        if not os.path.exists(path):
            r = requests.get(imgurl)
            with open(path, 'wb') as f:
                f.write(r.content)
                f.close()
                print('图片保存成功')
                return path
        else:
            print('图片已存在，保存失败')
            return path
        print('path')
    except:
        print("爬取失败")
    #time.sleep(1)

#构造SQL语句
def createSql(cartnames, imgurls, aurls, name_descs, updatetos, optypes, statuses):
    i = 0
    while i < len(aurls):
        val = (cartnames[i], imgurls[i], aurls[i], name_descs[i], updatetos[i], optypes[i], statuses[i], cartnames[i])
        print(val)
        sql = "INSERT INTO opera(op_name,op_photourl,op_url,op_desc,op_updateto,op_type,op_status,op_time) " \
              "SELECT %s,%s,%s,%s,%s,%s,%s,NOW() "\
              "from DUAL WHERE NOT EXISTS(SELECT op_name FROM opera WHERE op_name = %s)"
        if executeSql(sql=sql, values=val) == 0:
            sql = "UPDATE opera "\
                  "SET op_updateto =%s, op_url = %s, op_status = %s, op_photourl = %s, op_time = now() "\
                  "WHERE op_name = %s"
            val = (updatetos[i], aurls[i], statuses[i], imgurls[i], cartnames[i])
            executeSql(sql=sql, values=val)
        i = i + 1
        print(i)

# 执行sql语句
def executeSql(sql, values):
    conn = pymysql.connect(host='127.0.0.1', user='root', passwd='123456', db='cartoon', charset='utf8')
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
        print("插入数据异常" + Exception)
        conn.rollback()
    finally:
        # 关闭连接
        conn.close()
        print("HHHHH")
        return effect_row

if __name__ == '__main__':
    # 要爬取的网页链接
    # 腾讯视频offset=0第一页offset=30第二页其余不变,从0开始
    tpre = 'http://v.qq.com/x/list/cartoon?offset='
    tpix = '&iarea=1'
    turl = 'http://v.qq.com/x/list/cartoon?offset=0&iarea=1'
    # 爱奇艺视频11-1-1-iqiyi--第一页 11-2-1-iqiyi--第二页，从1开始
    ipre = 'https://list.iqiyi.com/www/4/37-------------11-'
    ipix = '-1-iqiyi--.html'
    iurl = 'https://list.iqiyi.com/www/4/37-------------11-1-1-iqiyi--.html'
    # 优酷分页显示，每页的地址在前一页的基础上加一  5_s_1_d_1.html第一页 5_s_1_d_2.html第二页其余都不变，从1开始
    upre = 'https://list.youku.com/category/show/c_100_a_%E4%B8%AD%E5%9B%BD_ag_5_s_4_d_1_p_'
    upix = '.html?spm=a2h1n.8251845.0.0'
    uurl = 'https://list.youku.com/category/show/c_100_a_%E4%B8%AD%E5%9B%BD_ag_5_s_4_d_1_p_1.html?spm=a2h1n.8251845.0.0'
    #每个网站爬取多少页pagenum
    pagenum = 14
    print(pagenum)
    for j in range(0,pagenum):
        k = str(j)
        turl = tpre + k + tpix
        i = str ( j + 1)
        uurl = upre + i + upix
        iurl = ipre + i + ipix
        getIQiOpera(iurl)
        getTXOpera(turl)
        getYouKuOpera(uurl)




