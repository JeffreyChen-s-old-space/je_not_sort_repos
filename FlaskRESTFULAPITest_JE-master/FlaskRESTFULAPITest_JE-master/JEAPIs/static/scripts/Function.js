function Image_Base64(Component, Image_Base64) {
    $(Component).attr("src", Image_Base64);
    $(Component).show();
}

function Change_Image(Component, Image_Path) {
    $(Component).show();
    $(Component).html('<img alt="Loading" src= ' + Image_Path + '>');
}

function Hide_Image(Component) {
    $(Component).hide();
}

function testLog() {
    console.log("test");
}

function test() {
    $.ajax({
        url: 'http://127.0.0.1:5000/Test',
        method: 'GET',
        error: function (err) {
            console.log(err.responseText)
        },
        success: function (res) {
            console.log(res);
            res = res.Data[0]
            console.log(res.test_data);
            console.log(res.test_name);
            console.log(res.test_key);

        }
    });
}


function Get_Code() {
    const request = new XMLHttpRequest();
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            if (request.readyState === 4)
                console.log(request.responseText);
        } else {
            console.log('err');
        }
    }
    request.onerror = function () {
        console.log('error')
    }
    request.open('GET', 'http://127.0.0.1:5000/Test', true)
    request.send()
}

function test_table(Component) {
    $.ajax({
        url: 'http://127.0.0.1:5000/Test_Data',
        method: 'GET',
        error: function (err) {
            console.log(err.responseText)
        },
        success: function (res) {
            console.log(res);
            count = Object.keys(res).length
            res = res.Data[0]
            console.log(res.test_data);
            console.log(res.test_name);
            console.log(res.test_key);
            console.log(count);
            for (i = 0; i < count; i++) {
                $(Component).append(
                    '<tbody>' +
                    '<tr>' +
                    '<th scope="row">' +
                    '<input name= ' + res.test_name + ' type="checkbox">' +
                    '</th>' +
                    '<td>' + res.test_data + '</td>' +
                    '<td>' + res.test_name + '</td>' +
                    '<td>' + res.test_key + '</td>' +
                    '</tr>' +
                    '</tbody>'
                )
            }
        }
    });
}
