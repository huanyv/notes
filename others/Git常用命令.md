# Git

* Git 常用命令大全：<https://blog.csdn.net/dengsilinming/article/details/8000622>

## 常用命令

* `git config --global user.name 用户名`设置用户签名
* `git config --global user.email 邮箱`设置用户签名
* `git init`初始化本地库
* `git status`查看本地库状态
* `git add 文件名`添加到暂存区
* `git commit -m "日志信息" 文件名`提交到本地库
* `git reflog`查看历史记录
* `git reset --hard 版本号`版本穿梭

## 分支操作

* `git branch 分支名`创建分支
* `git branch -v`查看分支
* `git checkout 分支名`切换分支
* `git branch -m oldname newname`修改分支名
* `git branch -m newname`更改当前分支名
* `git merge 分支名`把指定的分支合并到当前分支上

## 远程仓库操作


* `git remote -v`查看当前所有远程地址别名
* `git remote add 别名 远程地址`起别名
* `git push 别名 分支`推送本地分支上的内容到远程仓库
* `git pull 别名 分支`将远程仓库对于分支最新内容拉下来后与当前本地分支直接合并
* `git clone 远程地址`将远程仓库的内容克隆到本地

## 为什么要用Git

![](https://gitee.com/huanyv/imgbed/raw/master/img/20220315085432.png)