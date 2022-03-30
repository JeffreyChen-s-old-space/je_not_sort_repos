namespace WebView2_JE
{
    partial class Form1
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
            this.WebView = new Microsoft.Web.WebView2.WinForms.WebView2();
            this.Url_Text = new System.Windows.Forms.TextBox();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.上一頁 = new System.Windows.Forms.ToolStripMenuItem();
            this.下一頁 = new System.Windows.Forms.ToolStripMenuItem();
            this.重新整理 = new System.Windows.Forms.ToolStripMenuItem();
            this.停止 = new System.Windows.Forms.ToolStripMenuItem();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // WebView
            // 
            this.WebView.Font = new System.Drawing.Font("標楷體", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.WebView.Location = new System.Drawing.Point(11, 64);
            this.WebView.Name = "WebView";
            this.WebView.Size = new System.Drawing.Size(776, 374);
            this.WebView.Source = new System.Uri("https:\\\\google.com", System.UriKind.Absolute);
            this.WebView.TabIndex = 0;
            this.WebView.Text = "WebView";
            this.WebView.ZoomFactor = 1D;
            this.WebView.NavigationStarting += new System.EventHandler<Microsoft.Web.WebView2.Core.CoreWebView2NavigationStartingEventArgs>(this.WebView_NavigationStarting);
            // 
            // Url_Text
            // 
            this.Url_Text.Font = new System.Drawing.Font("標楷體", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(136)));
            this.Url_Text.Location = new System.Drawing.Point(11, 27);
            this.Url_Text.Name = "Url_Text";
            this.Url_Text.Size = new System.Drawing.Size(775, 31);
            this.Url_Text.TabIndex = 1;
            this.Url_Text.KeyDown += new System.Windows.Forms.KeyEventHandler(this.Url_Text_KeyDown);
            // 
            // menuStrip1
            // 
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.上一頁,
            this.下一頁,
            this.重新整理,
            this.停止});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(800, 28);
            this.menuStrip1.TabIndex = 2;
            this.menuStrip1.Text = "menuStrip1";
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
            // 重新整理
            // 
            this.重新整理.Name = "重新整理";
            this.重新整理.Size = new System.Drawing.Size(83, 24);
            this.重新整理.Text = "重新整理";
            this.重新整理.Click += new System.EventHandler(this.重新整理_Click);
            // 
            // 停止
            // 
            this.停止.Name = "停止";
            this.停止.Size = new System.Drawing.Size(53, 24);
            this.停止.Text = "停止";
            this.停止.Click += new System.EventHandler(this.停止_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoSize = true;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.Url_Text);
            this.Controls.Add(this.WebView);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.Resize += new System.EventHandler(this.Form1_Resize);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Microsoft.Web.WebView2.WinForms.WebView2 WebView;
        private System.Windows.Forms.TextBox Url_Text;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem 上一頁;
        private System.Windows.Forms.ToolStripMenuItem 下一頁;
        private System.Windows.Forms.ToolStripMenuItem 重新整理;
        private System.Windows.Forms.ToolStripMenuItem 停止;
    }
}

