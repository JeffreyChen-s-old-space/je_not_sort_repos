namespace Webbrowser
{
    partial class WebForm
    {
        /// <summary>
        /// 設計工具所需的變數。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清除任何使用中的資源。
        /// </summary>
        /// <param name="disposing">如果應該處置受控資源則為 true，否則為 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 設計工具產生的程式碼

        /// <summary>
        /// 此為設計工具支援所需的方法 - 請勿使用程式碼編輯器修改
        /// 這個方法的內容。
        /// </summary>
        private void InitializeComponent()
        {
            this.Main_Web = new System.Windows.Forms.WebBrowser();
            this.Url_Textbox = new System.Windows.Forms.TextBox();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.Web_Menu1 = new System.Windows.Forms.ToolStripMenuItem();
            this.返回搜尋頁面 = new System.Windows.Forms.ToolStripMenuItem();
            this.返回主頁 = new System.Windows.Forms.ToolStripMenuItem();
            this.上一頁 = new System.Windows.Forms.ToolStripMenuItem();
            this.下一頁 = new System.Windows.Forms.ToolStripMenuItem();
            this.額外功能 = new System.Windows.Forms.ToolStripMenuItem();
            this.列印 = new System.Windows.Forms.ToolStripMenuItem();
            this.重整網頁 = new System.Windows.Forms.ToolStripMenuItem();
            this.停止 = new System.Windows.Forms.ToolStripMenuItem();
            this.版面設定 = new System.Windows.Forms.ToolStripMenuItem();
            this.列印功能表 = new System.Windows.Forms.ToolStripMenuItem();
            this.列印預覽 = new System.Windows.Forms.ToolStripMenuItem();
            this.內容 = new System.Windows.Forms.ToolStripMenuItem();
            this.另存新檔 = new System.Windows.Forms.ToolStripMenuItem();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // Main_Web
            // 
            this.Main_Web.Location = new System.Drawing.Point(0, 67);
            this.Main_Web.MinimumSize = new System.Drawing.Size(20, 20);
            this.Main_Web.Name = "Main_Web";
            this.Main_Web.Size = new System.Drawing.Size(798, 421);
            this.Main_Web.TabIndex = 2;
            this.Main_Web.Url = new System.Uri("https://google.com", System.UriKind.Absolute);
            this.Main_Web.Navigated += new System.Windows.Forms.WebBrowserNavigatedEventHandler(this.Main_Web_Navigated);
            // 
            // Url_Textbox
            // 
            this.Url_Textbox.Font = new System.Drawing.Font("標楷體", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.Url_Textbox.Location = new System.Drawing.Point(0, 30);
            this.Url_Textbox.Name = "Url_Textbox";
            this.Url_Textbox.Size = new System.Drawing.Size(810, 31);
            this.Url_Textbox.TabIndex = 4;
            this.Url_Textbox.KeyDown += new System.Windows.Forms.KeyEventHandler(this.Url_Textbox_KeyDown);
            // 
            // menuStrip1
            // 
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.Web_Menu1,
            this.上一頁,
            this.下一頁,
            this.重整網頁,
            this.停止,
            this.額外功能});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(810, 28);
            this.menuStrip1.TabIndex = 5;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // Web_Menu1
            // 
            this.Web_Menu1.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.返回搜尋頁面,
            this.返回主頁});
            this.Web_Menu1.Name = "Web_Menu1";
            this.Web_Menu1.Size = new System.Drawing.Size(83, 24);
            this.Web_Menu1.Text = "網頁功能";
            // 
            // 返回搜尋頁面
            // 
            this.返回搜尋頁面.Name = "返回搜尋頁面";
            this.返回搜尋頁面.Size = new System.Drawing.Size(224, 26);
            this.返回搜尋頁面.Text = "返回搜尋頁面";
            this.返回搜尋頁面.Click += new System.EventHandler(this.返回搜尋頁面_Click);
            // 
            // 返回主頁
            // 
            this.返回主頁.Name = "返回主頁";
            this.返回主頁.Size = new System.Drawing.Size(224, 26);
            this.返回主頁.Text = "返回主頁";
            this.返回主頁.Click += new System.EventHandler(this.返回主頁_Click);
            // 
            // 上一頁
            // 
            this.上一頁.Name = "上一頁";
            this.上一頁.Size = new System.Drawing.Size(68, 24);
            this.上一頁.Text = "上一頁";
            this.上一頁.Click += new System.EventHandler(this.上一頁_Click);
            // 
            // 下一頁
            // 
            this.下一頁.Name = "下一頁";
            this.下一頁.Size = new System.Drawing.Size(68, 24);
            this.下一頁.Text = "下一頁";
            this.下一頁.Click += new System.EventHandler(this.下一頁_Click);
            // 
            // 額外功能
            // 
            this.額外功能.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.列印,
            this.版面設定,
            this.列印功能表,
            this.列印預覽,
            this.內容,
            this.另存新檔});
            this.額外功能.Name = "額外功能";
            this.額外功能.Size = new System.Drawing.Size(83, 24);
            this.額外功能.Text = "額外功能";
            // 
            // 列印
            // 
            this.列印.Name = "列印";
            this.列印.Size = new System.Drawing.Size(224, 26);
            this.列印.Text = "列印";
            this.列印.Click += new System.EventHandler(this.列印_Click);
            // 
            // 重整網頁
            // 
            this.重整網頁.Name = "重整網頁";
            this.重整網頁.Size = new System.Drawing.Size(83, 24);
            this.重整網頁.Text = "重整網頁";
            this.重整網頁.Click += new System.EventHandler(this.重整網頁_Click);
            // 
            // 停止
            // 
            this.停止.Name = "停止";
            this.停止.Size = new System.Drawing.Size(53, 24);
            this.停止.Text = "停止";
            this.停止.Click += new System.EventHandler(this.停止_Click);
            // 
            // 版面設定
            // 
            this.版面設定.Name = "版面設定";
            this.版面設定.Size = new System.Drawing.Size(224, 26);
            this.版面設定.Text = "版面設定";
            this.版面設定.Click += new System.EventHandler(this.版面設定_Click);
            // 
            // 列印功能表
            // 
            this.列印功能表.Name = "列印功能表";
            this.列印功能表.Size = new System.Drawing.Size(224, 26);
            this.列印功能表.Text = "列印功能表";
            this.列印功能表.Click += new System.EventHandler(this.列印功能表_Click);
            // 
            // 列印預覽
            // 
            this.列印預覽.Name = "列印預覽";
            this.列印預覽.Size = new System.Drawing.Size(224, 26);
            this.列印預覽.Text = "列印預覽";
            this.列印預覽.Click += new System.EventHandler(this.列印預覽_Click);
            // 
            // 內容
            // 
            this.內容.Name = "內容";
            this.內容.Size = new System.Drawing.Size(224, 26);
            this.內容.Text = "內容";
            this.內容.Click += new System.EventHandler(this.內容_Click);
            // 
            // 另存新檔
            // 
            this.另存新檔.Name = "另存新檔";
            this.另存新檔.Size = new System.Drawing.Size(224, 26);
            this.另存新檔.Text = "另存新檔";
            this.另存新檔.Click += new System.EventHandler(this.另存新檔_Click);
            // 
            // WebForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(11F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoScroll = true;
            this.AutoSize = true;
            this.ClientSize = new System.Drawing.Size(810, 500);
            this.Controls.Add(this.Url_Textbox);
            this.Controls.Add(this.Main_Web);
            this.Controls.Add(this.menuStrip1);
            this.Font = new System.Drawing.Font("標楷體", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "WebForm";
            this.Text = "WebForm";
            this.MaximumSizeChanged += new System.EventHandler(this.WebForm_Resize);
            this.MinimumSizeChanged += new System.EventHandler(this.WebForm_Resize);
            this.Load += new System.EventHandler(this.WebForm_Load);
            this.Resize += new System.EventHandler(this.WebForm_Resize);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.WebBrowser Main_Web;
        private System.Windows.Forms.TextBox Url_Textbox;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem Web_Menu1;
        private System.Windows.Forms.ToolStripMenuItem 返回搜尋頁面;
        private System.Windows.Forms.ToolStripMenuItem 返回主頁;
        private System.Windows.Forms.ToolStripMenuItem 上一頁;
        private System.Windows.Forms.ToolStripMenuItem 下一頁;
        private System.Windows.Forms.ToolStripMenuItem 額外功能;
        private System.Windows.Forms.ToolStripMenuItem 列印;
        private System.Windows.Forms.ToolStripMenuItem 重整網頁;
        private System.Windows.Forms.ToolStripMenuItem 停止;
        private System.Windows.Forms.ToolStripMenuItem 版面設定;
        private System.Windows.Forms.ToolStripMenuItem 列印功能表;
        private System.Windows.Forms.ToolStripMenuItem 列印預覽;
        private System.Windows.Forms.ToolStripMenuItem 內容;
        private System.Windows.Forms.ToolStripMenuItem 另存新檔;
    }
}

