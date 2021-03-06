define( [
     "dojo/_base/declare",
     "dijit/_WidgetBase",
     "dijit/_TemplatedMixin",
     "dijit/_WidgetsInTemplateMixin",
     "me/core/main_widgets/EnmeMainLayoutWidget",
     "me/core/enme",
     "dojo/text!me/web/widget/utils/template/toggleText.html" ],
    function(
    declare,
    _WidgetBase,
    _TemplatedMixin,
    _WidgetsInTemplateMixin,
    main_widget,
    _ENME,
     template ) {

  return declare( [ _WidgetBase, _TemplatedMixin, main_widget, _WidgetsInTemplateMixin ], {

     // Template string.
     templateString: template,
      /*
      * Default limit.
      */
     limit: 50,

     /*
      * Default text.
      */
     text: "",

     /*
      * Text after being shortened.
      */
     _shortText: "",

     /*
      *
      */
     _expanded: false,

     /*
      * Define the text to display.
      */
     _toggleText: function() {
         if ( !this._expanded ) {
              this._text.innerHTML = this.text;
         } else {
              this._text.innerHTML = this._shortText;
         }
         this._expanded = !this._expanded;
     },

     /*
      * Post create life cycle.
      */
     postCreate: function() {

         //Check if text exceeded the limit defined
         dojo.addClass( this._text, "p");
         if ( this.text.length > this.limit ) {
             this._shortText = this.text.substring( 0, this.limit );
             this._shortText = this._shortText.concat(" ");
             this._shortText = this._shortText.concat("...");
             this._text.innerHTML = this._shortText;
             dojo.addClass( this._text, "togglePointer");
              dojo.connect( this._text, "onclick", dojo.hitch( this, function( event ) {
                  this._toggleText();
              }) );
         } else {
             this._text.innerHTML = this.text;
         }
     }

  });
});
