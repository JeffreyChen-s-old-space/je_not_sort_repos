using System;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Webbrowser
{
    public partial class WebForm : Form
    {

        //用以判定網址
        public void Url_Navigate(String Url_Address)
        {
            if (String.IsNullOrEmpty(Url_Address)) return;
            if (Url_Address.Equals("about:blank")) return;
            if (!Url_Address.StartsWith("http://") && Url_Address.StartsWith("https://"))
            {
                Url_Address = "http://" + Url_Address;
            }
            try
            {
                Main_Web.Navigate(Url_Address);
            }catch(Exception Errr)
            {
                Console.WriteLine(Errr);
            }
        }

        public WebForm()
        {
            InitializeComponent();
        }

        //表單載入
        private void WebForm_Load(object sender, EventArgs e)
        {
            this.Resize += new EventHandler(this.WebForm_Resize);
        }

        //大小改變
        private void WebForm_Resize(object sender, EventArgs e)
        {
            Main_Web.Size = this.ClientSize - new Size(Main_Web.Location);
        }


        //在搜尋欄按下Enter
        private void Url_Textbox_KeyDown(object sender, KeyEventArgs e)
        {
            if(e.KeyCode == Keys.Enter)
            {
                Url_Navigate(Url_Textbox.Text);
            }

        }

        //網頁載入完成
        private void Main_Web_Navigated(object sender, WebBrowserNavigatedEventArgs e)
        {
            Url_Textbox.Text = Main_Web.Url.ToString();
        }

        private void 返回搜尋頁面_Click(object sender, EventArgs e)
        {
            Main_Web.GoSearch();
        }

        private void 返回主頁_Click(object sender, EventArgs e)
        {
            Main_Web.GoHome();
        }

        private void 上一頁_Click(object sender, EventArgs e)
        {
            if (Main_Web.CanGoBack)
                Main_Web.GoBack();
        }

        private void 下一頁_Click(object sender, EventArgs e)
        {
            if(Main_Web.CanGoForward)
                Main_Web.GoForward();
        }

        private void 重整網頁_Click(object sender, EventArgs e)
        {
            if(!Main_Web.Url.Equals("about:blank"))
                Main_Web.Refresh();
        }

        private void 停止_Click(object sender, EventArgs e)
        {
            Main_Web.Stop();
        }

        private void 列印_Click(object sender, EventArgs e)
        {
            Main_Web.Print();
        }

        private void 版面設定_Click(object sender, EventArgs e)
        {
            Main_Web.ShowPageSetupDialog();
        }

        private void 列印功能表_Click(object sender, EventArgs e)
        {
            Main_Web.ShowPrintDialog();
        }

        private void 列印預覽_Click(object sender, EventArgs e)
        {
            Main_Web.ShowPrintPreviewDialog();
        }

        private void 內容_Click(object sender, EventArgs e)
        {
            Main_Web.ShowPropertiesDialog();
        }

        private void 另存新檔_Click(object sender, EventArgs e)
        {
            Main_Web.ShowSaveAsDialog();
        }
    }
}
