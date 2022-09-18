import json

from flask import Blueprint, render_template, session, redirect, url_for, request
from flask_cors import cross_origin

from Project.Resource import RestfulAPIResource

Hash = RestfulAPIResource.Hash
SQL = RestfulAPIResource.SQL
ManagerStudentDetail = Blueprint('ManagerStudentDetail', __name__)


@ManagerStudentDetail.route(r'/GET/ManagerStudentDetail')
@cross_origin()
def manager_student_detail_page():
    if session.get('Login') == 'Login' and session.get('Access') == Hash.hash_sha512('Super'):
        return render_template('/Manager/ManagerStudentDetail.html')
    else:
        return redirect(url_for('Login.login_page'))


@ManagerStudentDetail.route(r'/PUT/StudentDetail', methods=['POST', ])
@cross_origin()
def manager_edit_student_detail():
    if request.method == 'POST':
        if request.form.get('method') == 'PUT':
            SQL.table_name = 'PersonnelDetail'
            PersonnelNumber = request.form.get('PersonnelNumber')
            PersonnelName = request.form.get('PersonnelName')
            EnrollYear = request.form.get('EnrollYear')
            SQL.insert_into_replace(PersonnelNumber, PersonnelName, EnrollYear)
    return render_template('/Manager/ManagerStudentDetail.html')


@ManagerStudentDetail.route(r'/GET/StudentDetail', methods=['GET', ])
@cross_origin()
def manager_student_detail():
    SQL.table_name = 'PersonnelDetail'
    SQL.select_prefix = '*'
    Details = SQL.select_form()
    print(Details)
    return json.dumps(Details)


@ManagerStudentDetail.route(r'/DELETE/StudentDetail', methods=['POST', ])
@cross_origin()
def manager_remove_student_detail():
    if request.method == 'POST':
        if request.form.get('method') == 'DELETE':
            SQL.table_name = 'PersonnelDetail'
            for keys in request.values:
                if keys == 'method':
                    pass
                else:
                    SQL.delete('PersonnelNumber', keys)
    return render_template('/Manager/ManagerStudentDetail.html')
