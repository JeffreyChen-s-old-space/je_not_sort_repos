# Title     : TODO
# Objective : TODO
# Created by: JE-Chen
# Created on: 2020/7/31
install.packages('wordcloud2')
install.packages('webshot')
install.packages('htmlwidgets')
library('wordcloud2')
library('webshot')
library('htmlwidgets')
webshot::install_phantomjs()
text_data <- read.csv('Test.csv')
text_png <- wordcloud2(text_data,0.5)
saveWidget(text_png,"Text.html",selfcontained = F)
webshot("Text.html","Clound.png", delay =5, vwidth = 480, vheight=480)
