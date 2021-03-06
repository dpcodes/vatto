(function(e) {
	jQuery.fn
			.extend({
				webarchScroll : function(t) {
					var n = t;
					this
							.each(function() {
								var t, r, i, s, o, u = "<div></div>", a = 30, f = false, l = n
										|| {}, c = parseInt(l.wheelStep) || 20, h = l.width
										|| "100%", p = l.height || "auto", d = l.size
										|| "7px", v = l.color || "#000", m = l.position
										|| "right", g = l.distance || "1px", y = l.start
										|| "top", b = l.opacity || .4, w = true || l.alwaysVisible === true, E = l.railVisible || true, S = l.railColor
										|| "#333", x = l.railOpacity || .2;
								var T = e(this);
								var N = T.parent();
								var C = T.parent().addClass("slimScrollDiv");
								N.css({
									overflow : "hidden"
								});
								T.css({
									overflow : "hidden",
									height : "100%"
								});
								var k = e(u).css({
									width : d,
									height : "100%",
									position : "absolute",
									top : 0,
									display : w && E ? "block" : "none",
									"border-radius" : d,
									background : S,
									opacity : x,
									zIndex : 90
								});
								var L = e(u).attr({
									"class" : "slimScrollBar ",
									style : "border-radius: " + d
								}).css({
									background : v,
									width : d,
									position : "absolute",
									top : 0,
									opacity : b,
									display : w ? "block" : "none",
									BorderRadius : d,
									MozBorderRadius : d,
									WebkitBorderRadius : d,
									zIndex : 99
								});
								var A = m == "right" ? {
									right : g
								} : {
									left : g
								};
								k.css(A);
								L.css(A);
								N.append(L);
								N.append(k);
								L.draggable({
									axis : "y",
									containment : "parent",
									start : function() {
										i = true
									},
									stop : function() {
										i = false;
										H()
									},
									drag : function(t) {
										M(0, e(this).position().top, false)
									}
								});
								k.hover(function() {
									P()
								}, function() {
									H()
								});
								L.hover(function() {
									r = true
								}, function() {
									r = false
								});
								T.hover(function() {
									t = true;
									P();
									H()
								}, function() {
									t = false;
									H()
								});
								var O = function(e) {
									if (!t) {
										return
									}
									var e = e || window.event;
									var n = 0;
									if (e.wheelDelta) {
										n = -e.wheelDelta / 120
									}
									if (e.detail) {
										n = e.detail / 3
									}
									M(n, true);
									if (e.preventDefault && !f) {
										e.preventDefault()
									}
									if (!f) {
										e.returnValue = false
									}
								};
								var M = function(e, t, n) {
									var r = e;
									if (t) {
										r = L.position().top + e * c;
										r = Math.max(r, 0);
										var i = N.outerHeight()
												- L.outerHeight();
										r = Math.min(r, i);
										L.css({
											top : r + "px"
										})
									}
									var s = parseInt(L.position().top)
											/ (N.outerHeight() - L
													.outerHeight());
									r = s
											* (T[0].scrollHeight - N
													.outerHeight());
									if (n) {
										r = e;
										var o = r / T[0].scrollHeight
												* N.outerHeight();
										L.css({
											top : o + "px"
										})
									}
									T.scrollTop(r);
									P()
								};
								var _ = function() {
									if (window.addEventListener) {
										this.addEventListener("DOMMouseScroll",
												O, false);
										this.addEventListener("mousewheel", O,
												false)
									} else {
										document.attachEvent("onmousewheel", O)
									}
								};
								_();
								var D = function() {
									o = Math.max(N.outerHeight()
											/ T[0].scrollHeight
											* N.outerHeight(), a);
									L.css({
										height : o + "px"
									})
								};
								D();
								var P = function() {
									D();
									clearTimeout(s);
									if (o >= T.outerHeight()) {
										f = true;
										return
									}
									L.fadeIn("fast");
									if (E) {
										k.fadeIn("fast")
									}
								};
								var H = function() {
									if (!w) {
										s = setTimeout(function() {
											if (!r && !i) {
												L.fadeOut("slow");
												k.fadeOut("slow")
											}
										}, 1e3);
									}
								};
								if (y == "bottom") {
									L.css({
										top : "auto",
										bottom : 0
									});
									M(0, true);
								} else if (typeof y == "object") {
									M(e(y).position().top, null, true);
									if (!w) {
										L.hide();
									}
								}
							});
					return this;
				}
			});
	jQuery.fn.extend({
		webarchScroll : jQuery.fn.webarchScroll
	});
})(jQuery);