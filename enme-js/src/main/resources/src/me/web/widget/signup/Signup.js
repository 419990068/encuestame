/*
 * Copyright 2013 encuestame
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/***
 *  @author juanpicado19D0Tgm@ilDOTcom
 *  @version 1.146
 *  @module SignUp
 *  @namespace Widgets
 *  @class SignUp
 */
define( [
         "dojo/_base/declare",
		 "dojo/dom",
         "dijit/_WidgetBase",
         "dijit/_TemplatedMixin",
         "dijit/_WidgetsInTemplateMixin",
         "dijit/form/Form",
         "me/core/main_widgets/EnmeMainLayoutWidget",
         "me/core/enme",
         "me/web/widget/validator/RealNameValidator",
         "me/web/widget/validator/PasswordValidator",
         "me/web/widget/validator/EmailValidator",
         "me/web/widget/validator/UsernameValidator",
         "dijit/registry",
         "dojo/text!me/web/widget/signup/templates/signup.html" ],
        function(
                declare,
                dom,
                _WidgetBase,
                _TemplatedMixin,
                _WidgetsInTemplateMixin,
                Form,
                main_widget,
                _ENME,
                RealNameValidator,
                PasswordValidator,
                EmailValidator,
                UsernameValidator,
                registry,
                template ) {
            return declare( [ _WidgetBase, _TemplatedMixin, main_widget, _WidgetsInTemplateMixin ], {

       /*
        * Template string.
        */
       templateString: template,

       /*
        *
        */
        value: "Sign Up Now",

       /*
        *
        */
       userWidget: null,

       /*
        *
        */
       passWidget: null,

       /*
        *
        */
       emailWidget: null,

       /*
        *
        */
       realWidget: null,

       /*
        *
        */
       standWidget: null,

       /**
        *
        */
       postCreate: function() {
           var rn = dom.byId("rm"),
           pssw = dom.byId("pssw"),
           em = dom.byId("em"),
           _form = dom.byId( "signupForm" ),
           usrva = dom.byId("usrva");
           this.passWidget =  new PasswordValidator({
               id: "password" + this.id,
               enviroment: "ext"
           });
           this.userWidget =  new UsernameValidator({
               id: "username_" + this.id,
               enviroment: "ext"
           });
           this.realWidget =  new RealNameValidator({
               id: "real_name" + this.id,
               enviroment: "ext"
           });
           this.emailWidget = new EmailValidator({
               id: "email_user" + this.id,
               enviroment: "ext"
           });
           var widget_form = new Form();
           rn.appendChild( this.realWidget.domNode );
           pssw.appendChild( this.passWidget.domNode );
           em.appendChild( this.emailWidget.domNode );
           usrva.appendChild( this.userWidget.domNode );
           _form.appendChild( widget_form.domNode );
          if ( this.userWidget === null ||
                    this.passWidget === null ||
                    this.emailWidget === null ||
                    this.realWidget === null ) {
                       throw new Error("NO WIDGETS FOUND");
                   }

                   // Prevent cowboy user handler
                   dojo.connect( this._input, "ondoubleclick", dojo.hitch( this, function( event ) {
                         event.preventDefault();
                         return false;
                   }) );
           dojo.subscribe("/encuestame/singup/validate", this, this._checkValidWidgets );
       },

       /**
        *
        */
       _onSubmit: function( event ) {
           dojo.stopEvent( event );
           this._checkValidWidgets();
       },

       /**
        *
        * @method
        */
       blockSubmitButton: function() {
          this._submit.disabled = true;
       },

       /**
        *
        */
       createNewAccountService: function() {
            this.blockSubmitButton();
            signupForm.submit();
       },

       /**
        *
        */
       _checkValidWidgets: function() {
           var that = this,
           _submit = function() {
               that.createNewAccountService( this.userWidget, this.passWidget, this.emailWidget, this.realWidget );
           };
           if ( !this.userWidget.isValid ) {
               this.userWidget.recheck("username");
           } else if ( !this.userWidget.isValid ) {
               this.userWidget.recheck("username");
           } else if ( !this.passWidget.isValid ) {
               this.passWidget.validatePassword();
           } else if ( !this.emailWidget.isValid ) {
               this.emailWidget.recheck("email");
           } else if ( !this.realWidget.isValid ) {
               this.realWidget.recheck("real_name");
           } else {
               _submit();
           }
       },

       /**
        *
        */
       _create: function( event ) {
           this.uploadImage();
       }

    });
});
