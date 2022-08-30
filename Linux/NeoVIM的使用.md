# NeoVIM的使用

[toc]

## 1. 安装

1. vim下载链接：[https://github.com/neovim/neovim/releases](https://github.com/neovim/neovim/releases)
2. vim-plug：[https://github.com/junegunn/vim-plug#installation](https://github.com/junegunn/vim-plug#installation)

## 2. 配置

### 2.1 插件配置

* 下载pulg.vim后放到 `~/AppData\Local\nvim-data\site\autoload\plug.vim`下

### 2.2 neovim配置

#### 2.2.1 插件

* neovim配置文件在 `~\AppData\Local\nvim\init.vim`
* 插件配置
* 插件列表必须在 `begin`和 `end`函数之间
* 退出vim配置文件，再进入
* 执行 `:PlugInstall`，显示 `Done`安装成功

```vim
call plug#begin('~/.vim/plugged')

Plug 'scrooloose/nerdtree'

call plug#end()
```

#### 2.2.2 设置

```vim
" 编码
set encoding=utf-8
" 开启行号
set nu
" 相对行号
set relativenumber

" tab为4个空格
set ts=4
" 编辑模式的时候按退格键的时候退回缩进的长度
set softtabstop=4
" 每一级缩进的长度
set shiftwidth=4
" 缩进用空格来表示，noexpandtab 则是用制表符表示一个缩进。
set expandtab
" 自动缩进
set autoindent
```

#### 2.2.3 映射

```vim
" jk映射成esc键
imap jk <Esc>

" ctl + e 打开NERDTree
map <silent> <C-e> :NERDTreeToggle<CR>
```

## 3. NERDTree插件的使用

* 输入 `:NERDTree`打开
* [https://blog.csdn.net/weixin_37926734/article/details/124919260](https://blog.csdn.net/weixin_37926734/article/details/124919260)
* [https://www.cnblogs.com/tiny1987/p/15657192.html](https://www.cnblogs.com/tiny1987/p/15657192.html)

```
ctrl + w + h     光标 focus 左侧树形目录
ctrl + w + l     光标 focus 右侧文件显示窗口
ctrl + w + w     光标自动在左右侧窗口切换
ctrl + w + r     移动当前窗口的布局位置

o      在已有窗口中打开文件、目录或书签，并跳到该窗口
go     在已有窗口 中打开文件、目录或书签，但不跳到该窗口
t      在新 Tab 中打开选中文件/书签，并跳到新 Tab
T      在新 Tab 中打开选中文件/书签，但不跳到新 Tab
i      split 一个新窗口打开选中文件，并跳到该窗口
gi     split 一个新窗口打开选中文件，但不跳到该窗口
s      vsplit 一个新窗口打开选中文件，并跳到该窗口
gs     vsplit 一个新 窗口打开选中文件，但不跳到该窗口
!      执行当前文件
O      递归打开选中 结点下的所有目录
m      文件操作：复制、删除、移动等

:tabnew [++opt选项] ［＋cmd］ 文件      建立对指定文件新的tab
:tabc   关闭当前的 tab
:tabo   关闭所有其他的 tab
:tabs   查看所有打开的 tab
:tabp   前一个 tab
:tabn   后一个 tab

标准模式下：
gT      前一个 tab
gt      后一个 tab
```

```
?: 快速帮助文档
x: 收起当前打开的目录
X: 收起所有打开的目录
e: 以文件管理的方式打开选中的目录
D: 删除书签
P: 大写，跳转到当前根路径
p: 小写，跳转到光标所在的上一级路径
K: 跳转到第一个子路径
J: 跳转到最后一个子路径
<C-j>和<C-k>: 在同级目录和文件间移动，忽略子目录和子文件
C: 将根路径设置为光标所在的目录
u: 设置上级目录为根路径
U: 设置上级目录为跟路径，但是维持原来目录打开的状态
r: 刷新光标所在的目录
R: 刷新当前根路径
I: 显示或者不显示隐藏文件
f: 打开和关闭文件过滤器
q: 关闭NERDTree
A: 全屏显示NERDTree，或者关闭全屏
```
