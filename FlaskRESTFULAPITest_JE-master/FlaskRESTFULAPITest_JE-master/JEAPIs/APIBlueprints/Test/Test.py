import json

from flask import Blueprint, jsonify, request
from flask_cors import cross_origin

Test = Blueprint('Test', __name__)

Data = [
    {
        'test_name': 'Test Name',
        'test_data': 'Test Data',
        'test_key': 'test_Value'
    }
]


@Test.route(r'/Test_Data')
@cross_origin()
def test_data():
    return jsonify({'Data': Data})


@Test.route(r'/Test')
@cross_origin()
def test():
    return "test"


@Test.route(r'/Test_Post', methods=['POST', ])
@cross_origin()
def test_post():
    if request.method == 'POST':
        for k in request.values:
            print(k)
    return "ooo"


@Test.route(r'/Test_Select', methods=['POST', ])
@cross_origin()
def test_select():
    if request.method == 'POST':
        print(request.form.get('555'))
    return "ooo"


@Test.route(r'/Test_Data/<data_name>')
@cross_origin()
def test_data_with_name(data_name):
    if Data[0].get(data_name) is not None:
        return json.dumps(Data[0][data_name])
    else:
        return '無此值'
