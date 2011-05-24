package com.immani.mydwich

import org.apache.shiro.SecurityUtils

class LoginTagLib {
	def loginControl = {
		if(SecurityUtils.subject.authenticated){
			out << "Hello ${SecurityUtils.subject.principal} "
			out << """[${link(action:"signOut", controller:"auth"){"Logout"}}]"""
		} else {
			out << """[${link(action:"login", controller:"auth"){"Login"}}]"""
		}
	}
}