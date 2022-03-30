using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Microsoft.Web.WebView2.Core;

namespace WebView2_JE
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            this.Resize += new EventHandler(this.Form1_Resize);
            InitializeAsync();
        }

        async void InitializeAsync()
        {
            await WebView.EnsureCoreWebView2Async(null);
            WebView.CoreWebView2.WebMessageReceived += UpdateAddressBar;

            await WebView.CoreWebView2.AddScriptToExecuteOnDocumentCreatedAsync("window.chrome.webview.postMessage(window.document.URL);");
            //await WebView.CoreWebView2.AddScriptToExecuteOnDocumentCreatedAsync("window.chrome.webview.addEventListener(\'message\', event => alert(event.data));");
        }

        void UpdateAddressBar(object sender, CoreWebView2WebMessageReceivedEventArgs args)
        {
            String uri = args.TryGetWebMessageAsString();
            Url_Text.Text = uri;
            WebView.CoreWebView2.PostWebMessageAsString(uri);
        }

        //用以判定網址
        public void Url_Navigate(String Url_Address)
        {
            if (String.IsNullOrEmpty(Url_Address)) return;
            if (Url_Address.Equals("about:blank")) return;
            if (!Url_Address.StartsWith("http://") && !Url_Address.StartsWith("https://"))
            {
                Url_Address = "https://" + Url_Address;
            }
            try
            {
                Console.WriteLine(Url_Address);
                WebView.CoreWebView2.Navigate(Url_Address);
            }
            catch (Exception Errr)
            {
                Console.WriteLine(Errr);
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            WebView.Size = this.ClientSize - new Size(WebView.Location);
            Url_Text.Width = this.Width;
        }

        private void Form1_Resize(object sender, EventArgs e)
        {
            WebView.Size = this.ClientSize - new Size(WebView.Location);
            Url_Text.Width = this.Width;
        }

        private void Url_Text_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
                Url_Navigate(Url_Text.Text);
        }

        private void WebView_NavigationStarting(object sender, CoreWebView2NavigationStartingEventArgs e)
        {
            if (!e.Uri.StartsWith("https://"))
            {
                Console.WriteLine(e.Uri);
                WebView.CoreWebView2.ExecuteScriptAsync($"alert('{e.Uri} http不安全 請改用https')");
                e.Cancel = true;
            }
        }

        private void 上一頁_Click(object sender, EventArgs e)
        {
            if (WebView.CanGoBack)
            {
                WebView.GoBack();
            }

        }
        private void 下一頁_Click(object sender, EventArgs e)
        {
            if (WebView.CanGoForward)
            {
                WebView.GoForward();
            }
        }

        private void 重新整理_Click(object sender, EventArgs e)
        {
            WebView.Reload();
        }

        private void 停止_Click(object sender, EventArgs e)
        {
            WebView.Stop();
        }
    }
}
