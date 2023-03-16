## vimrc

```
set encoding=utf-8

imap jk <Esc>

inoremap [ []<Esc>i
inoremap { {}<Esc>i
inoremap < <><Esc>i
inoremap ( ()<Esc>i
inoremap ' ''<Esc>i
inoremap " ""<Esc>i

" 设置行号
set number
" 相对行号
set relativenumber

" tab为4个空格
set tabstop=4
" 缩进为4空格
set shiftwidth=4
" 自动缩进
set autoindent
" 空格代替制表符
set expandtab

" 高亮当前行
set cursorline
" 括号匹配高亮
set showmatch
" 显示当前光标位置
set ruler
" 出错不响
set noerrorbells 
set visualbell
```

## ideavim

```
"" Source your .vimrc
"source ~/.vimrc

" Plug
Plug 'preservim/nerdtree'

"" -- Suggested options --
" Show a few lines of context around the cursor. Note that this makes the
" text scroll if you mouse-click near the start or end of the window.
set scrolloff=5

" Do incremental searching.
set incsearch

" 相对行号
set nu
set relativenumber
" set noerrorbells
set visualbell

" 重构保持模式
set idearefactormode=keep

" 智能合并行
set ideajoin

" Don't use Ex mode, use Q for formatting.
map Q gq

" 切换到上一个文件
map gr gT

" 设置与系统粘贴版交互的快捷键
map <C-c> "+y
" map <C-v> "+p

" jk映射成ESC
imap jk <Esc>

"" -- Map IDE actions to IdeaVim -- https://jb.gg/abva4t
"" Map \r to the Reformat Code action
"map \r <Action>(ReformatCode)

"" Map <leader>d to start debug
"map <leader>d <Action>(Debug)

"" Map \b to toggle the breakpoint on the current line
"map \b <Action>(ToggleLineBreakpoint)


" Find more examples here: https://jb.gg/share-ideavimrc
```