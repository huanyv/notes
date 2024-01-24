# NVM安装指南

* nvm可以方便的切换node版本

## 下载

* GitHub：<https://github.com/nxshell/nxshell/releases>

## 配置

1. 打开nvm安装目录
2. setting.txt
3. 淘宝镜像：<https://www.npmmirror.com/>

```txt
node_mirror: http://npmmirror.com/mirrors/node/
npm_mirror: http://npmmirror.com/mirrors/npm/
```

## 使用

* 执行`nvm --help`查看帮助
* 安装后执行`node -v`和`npm -v`验证

```
# 查看网络可以安装的 node 版本
nvm list available

# 安装 指定版本 (16.5.0)
nvm install 16.5.0

# 查看已安装版本
nvm ls 

# 使用 指定版本
nvm use 16.5.0

# 卸载 指定版本
nvm uninstall 16.5.0
```


