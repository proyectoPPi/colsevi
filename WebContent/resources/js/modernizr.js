/*! modernizr 3.4.0 (Custom Build) | MIT *
 * https://modernizr.com/download/?-csstransitions-domprefixes-mq-prefixed-prefixedcss-prefixedcssvalue-prefixes-printshiv-setclasses-testallprops-testprop-teststyles !*/
! function(e, t, n) {
    function r(e, t) {
        return typeof e === t
    }

    function o() {
        var e, t, n, o, i, a, s;
        for (var l in C)
            if (C.hasOwnProperty(l)) {
                if (e = [], t = C[l], t.name && (e.push(t.name.toLowerCase()), t.options && t.options.aliases && t.options.aliases.length))
                    for (n = 0; n < t.options.aliases.length; n++) e.push(t.options.aliases[n].toLowerCase());
                for (o = r(t.fn, "function") ? t.fn() : t.fn, i = 0; i < e.length; i++) a = e[i], s = a.split("."), 1 === s.length ? Modernizr[s[0]] = o : (!Modernizr[s[0]] || Modernizr[s[0]] instanceof Boolean || (Modernizr[s[0]] = new Boolean(Modernizr[s[0]])), Modernizr[s[0]][s[1]] = o), S.push((o ? "" : "no-") + s.join("-"))
            }
    }

    function i(e) {
        var t = x.className,
            n = Modernizr._config.classPrefix || "";
        if (_ && (t = t.baseVal), Modernizr._config.enableJSClass) {
            var r = new RegExp("(^|\\s)" + n + "no-js(\\s|$)");
            t = t.replace(r, "$1" + n + "js$2")
        }
        Modernizr._config.enableClasses && (t += " " + n + e.join(" " + n), _ ? x.className.baseVal = t : x.className = t)
    }

    function a(e) {
        return e.replace(/([a-z])-([a-z])/g, function(e, t, n) {
            return t + n.toUpperCase()
        }).replace(/^-/, "")
    }

    function s(e) {
        return e.replace(/([A-Z])/g, function(e, t) {
            return "-" + t.toLowerCase()
        }).replace(/^ms-/, "-ms-")
    }

    function l() {
        return "function" != typeof t.createElement ? t.createElement(arguments[0]) : _ ? t.createElementNS.call(t, "http://www.w3.org/2000/svg", arguments[0]) : t.createElement.apply(t, arguments)
    }

    function u() {
        var e = t.body;
        return e || (e = l(_ ? "svg" : "body"), e.fake = !0), e
    }

    function f(e, n, r, o) {
        var i, a, s, f, c = "modernizr",
            d = l("div"),
            p = u();
        if (parseInt(r, 10))
            for (; r--;) s = l("div"), s.id = o ? o[r] : c + (r + 1), d.appendChild(s);
        return i = l("style"), i.type = "text/css", i.id = "s" + c, (p.fake ? p : d).appendChild(i), p.appendChild(d), i.styleSheet ? i.styleSheet.cssText = e : i.appendChild(t.createTextNode(e)), d.id = c, p.fake && (p.style.background = "", p.style.overflow = "hidden", f = x.style.overflow, x.style.overflow = "hidden", x.appendChild(p)), a = n(d, e), p.fake ? (p.parentNode.removeChild(p), x.style.overflow = f, x.offsetHeight) : d.parentNode.removeChild(d), !!a
    }

    function c(e, t) {
        return !!~("" + e).indexOf(t)
    }

    function d(e, t) {
        return function() {
            return e.apply(t, arguments)
        }
    }

    function p(e, t, n) {
        var o;
        for (var i in e)
            if (e[i] in t) return n === !1 ? e[i] : (o = t[e[i]], r(o, "function") ? d(o, n || t) : o);
        return !1
    }

    function m(t, n, r) {
        var o;
        if ("getComputedStyle" in e) {
            o = getComputedStyle.call(e, t, n);
            var i = e.console;
            if (null !== o) r && (o = o.getPropertyValue(r));
            else if (i) {
                var a = i.error ? "error" : "log";
                i[a].call(i, "getComputedStyle returning null, its possible modernizr test results are inaccurate")
            }
        } else o = !n && t.currentStyle && t.currentStyle[r];
        return o
    }

    function h(t, r) {
        var o = t.length;
        if ("CSS" in e && "supports" in e.CSS) {
            for (; o--;)
                if (e.CSS.supports(s(t[o]), r)) return !0;
            return !1
        }
        if ("CSSSupportsRule" in e) {
            for (var i = []; o--;) i.push("(" + s(t[o]) + ":" + r + ")");
            return i = i.join(" or "), f("@supports (" + i + ") { #modernizr { position: absolute; } }", function(e) {
                return "absolute" == m(e, null, "position")
            })
        }
        return n
    }

    function v(e, t, o, i) {
        function s() {
            f && (delete F.style, delete F.modElem)
        }
        if (i = r(i, "undefined") ? !1 : i, !r(o, "undefined")) {
            var u = h(e, o);
            if (!r(u, "undefined")) return u
        }
        for (var f, d, p, m, v, g = ["modernizr", "tspan", "samp"]; !F.style && g.length;) f = !0, F.modElem = l(g.shift()), F.style = F.modElem.style;
        for (p = e.length, d = 0; p > d; d++)
            if (m = e[d], v = F.style[m], c(m, "-") && (m = a(m)), F.style[m] !== n) {
                if (i || r(o, "undefined")) return s(), "pfx" == t ? m : !0;
                try {
                    F.style[m] = o
                } catch (y) {}
                if (F.style[m] != v) return s(), "pfx" == t ? m : !0
            }
        return s(), !1
    }

    function g(e, t, n, o, i) {
        var a = e.charAt(0).toUpperCase() + e.slice(1),
            s = (e + " " + z.join(a + " ") + a).split(" ");
        return r(t, "string") || r(t, "undefined") ? v(s, t, o, i) : (s = (e + " " + N.join(a + " ") + a).split(" "), p(s, t, n))
    }

    function y(e, t, r) {
        return g(e, n, n, t, r)
    }
    var S = [],
        C = [],
        E = {
            _version: "3.4.0",
            _config: {
                classPrefix: "",
                enableClasses: !0,
                enableJSClass: !0,
                usePrefixes: !0
            },
            _q: [],
            on: function(e, t) {
                var n = this;
                setTimeout(function() {
                    t(n[e])
                }, 0)
            },
            addTest: function(e, t, n) {
                C.push({
                    name: e,
                    fn: t,
                    options: n
                })
            },
            addAsyncTest: function(e) {
                C.push({
                    name: null,
                    fn: e
                })
            }
        },
        Modernizr = function() {};
    Modernizr.prototype = E, Modernizr = new Modernizr;
    var b = E._config.usePrefixes ? " -webkit- -moz- -o- -ms- ".split(" ") : ["", ""];
    E._prefixes = b;
    var x = t.documentElement,
        _ = "svg" === x.nodeName.toLowerCase();
    _ || ! function(e, t) {
        function n(e, t) {
            var n = e.createElement("p"),
                r = e.getElementsByTagName("head")[0] || e.documentElement;
            return n.innerHTML = "x<style>" + t + "</style>", r.insertBefore(n.lastChild, r.firstChild)
        }

        function r() {
            var e = _.elements;
            return "string" == typeof e ? e.split(" ") : e
        }

        function o(e, t) {
            var n = _.elements;
            "string" != typeof n && (n = n.join(" ")), "string" != typeof e && (e = e.join(" ")), _.elements = n + " " + e, u(t)
        }

        function i(e) {
            var t = x[e[E]];
            return t || (t = {}, b++, e[E] = b, x[b] = t), t
        }

        function a(e, n, r) {
            if (n || (n = t), v) return n.createElement(e);
            r || (r = i(n));
            var o;
            return o = r.cache[e] ? r.cache[e].cloneNode() : C.test(e) ? (r.cache[e] = r.createElem(e)).cloneNode() : r.createElem(e), !o.canHaveChildren || S.test(e) || o.tagUrn ? o : r.frag.appendChild(o)
        }

        function s(e, n) {
            if (e || (e = t), v) return e.createDocumentFragment();
            n = n || i(e);
            for (var o = n.frag.cloneNode(), a = 0, s = r(), l = s.length; l > a; a++) o.createElement(s[a]);
            return o
        }

        function l(e, t) {
            t.cache || (t.cache = {}, t.createElem = e.createElement, t.createFrag = e.createDocumentFragment, t.frag = t.createFrag()), e.createElement = function(n) {
                return _.shivMethods ? a(n, e, t) : t.createElem(n)
            }, e.createDocumentFragment = Function("h,f", "return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&(" + r().join().replace(/[\w\-:]+/g, function(e) {
                return t.createElem(e), t.frag.createElement(e), 'c("' + e + '")'
            }) + ");return n}")(_, t.frag)
        }

        function u(e) {
            e || (e = t);
            var r = i(e);
            return !_.shivCSS || h || r.hasCSS || (r.hasCSS = !!n(e, "article,aside,dialog,figcaption,figure,footer,header,hgroup,main,nav,section{display:block}mark{background:#FF0;color:#000}template{display:none}")), v || l(e, r), e
        }

        function f(e) {
            for (var t, n = e.getElementsByTagName("*"), o = n.length, i = RegExp("^(?:" + r().join("|") + ")$", "i"), a = []; o--;) t = n[o], i.test(t.nodeName) && a.push(t.applyElement(c(t)));
            return a
        }

        function c(e) {
            for (var t, n = e.attributes, r = n.length, o = e.ownerDocument.createElement(N + ":" + e.nodeName); r--;) t = n[r], t.specified && o.setAttribute(t.nodeName, t.nodeValue);
            return o.style.cssText = e.style.cssText, o
        }

        function d(e) {
            for (var t, n = e.split("{"), o = n.length, i = RegExp("(^|[\\s,>+~])(" + r().join("|") + ")(?=[[\\s,>+~#.:]|$)", "gi"), a = "$1" + N + "\\:$2"; o--;) t = n[o] = n[o].split("}"), t[t.length - 1] = t[t.length - 1].replace(i, a), n[o] = t.join("}");
            return n.join("{")
        }

        function p(e) {
            for (var t = e.length; t--;) e[t].removeNode()
        }

        function m(e) {
            function t() {
                clearTimeout(a._removeSheetTimer), r && r.removeNode(!0), r = null
            }
            var r, o, a = i(e),
                s = e.namespaces,
                l = e.parentWindow;
            return !T || e.printShived ? e : ("undefined" == typeof s[N] && s.add(N), l.attachEvent("onbeforeprint", function() {
                t();
                for (var i, a, s, l = e.styleSheets, u = [], c = l.length, p = Array(c); c--;) p[c] = l[c];
                for (; s = p.pop();)
                    if (!s.disabled && w.test(s.media)) {
                        try {
                            i = s.imports, a = i.length
                        } catch (m) {
                            a = 0
                        }
                        for (c = 0; a > c; c++) p.push(i[c]);
                        try {
                            u.push(s.cssText)
                        } catch (m) {}
                    }
                u = d(u.reverse().join("")), o = f(e), r = n(e, u)
            }), l.attachEvent("onafterprint", function() {
                p(o), clearTimeout(a._removeSheetTimer), a._removeSheetTimer = setTimeout(t, 500)
            }), e.printShived = !0, e)
        }
        var h, v, g = "3.7.3",
            y = e.html5 || {},
            S = /^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i,
            C = /^(?:a|b|code|div|fieldset|h1|h2|h3|h4|h5|h6|i|label|li|ol|p|q|span|strong|style|table|tbody|td|th|tr|ul)$/i,
            E = "_html5shiv",
            b = 0,
            x = {};
        ! function() {
            try {
                var e = t.createElement("a");
                e.innerHTML = "<xyz></xyz>", h = "hidden" in e, v = 1 == e.childNodes.length || function() {
                    t.createElement("a");
                    var e = t.createDocumentFragment();
                    return "undefined" == typeof e.cloneNode || "undefined" == typeof e.createDocumentFragment || "undefined" == typeof e.createElement
                }()
            } catch (n) {
                h = !0, v = !0
            }
        }();
        var _ = {
            elements: y.elements || "abbr article aside audio bdi canvas data datalist details dialog figcaption figure footer header hgroup main mark meter nav output picture progress section summary template time video",
            version: g,
            shivCSS: y.shivCSS !== !1,
            supportsUnknownElements: v,
            shivMethods: y.shivMethods !== !1,
            type: "default",
            shivDocument: u,
            createElement: a,
            createDocumentFragment: s,
            addElements: o
        };
        e.html5 = _, u(t);
        var w = /^$|\b(?:all|print)\b/,
            N = "html5shiv",
            T = !v && function() {
                var n = t.documentElement;
                return !("undefined" == typeof t.namespaces || "undefined" == typeof t.parentWindow || "undefined" == typeof n.applyElement || "undefined" == typeof n.removeNode || "undefined" == typeof e.attachEvent)
            }();
        _.type += " print", _.shivPrint = m, m(t), "object" == typeof module && module.exports && (module.exports = _)
    }("undefined" != typeof e ? e : this, t);
    var w = "Moz O ms Webkit",
        N = E._config.usePrefixes ? w.toLowerCase().split(" ") : [];
    E._domPrefixes = N;
    var T = function(e, t) {
        var n = !1,
            r = l("div"),
            o = r.style;
        if (e in o) {
            var i = N.length;
            for (o[e] = t, n = o[e]; i-- && !n;) o[e] = "-" + N[i] + "-" + t, n = o[e]
        }
        return "" === n && (n = !1), n
    };
    E.prefixedCSSValue = T;
    var j = function() {
        var t = e.matchMedia || e.msMatchMedia;
        return t ? function(e) {
            var n = t(e);
            return n && n.matches || !1
        } : function(t) {
            var n = !1;
            return f("@media " + t + " { #modernizr { position: absolute; } }", function(t) {
                n = "absolute" == (e.getComputedStyle ? e.getComputedStyle(t, null) : t.currentStyle).position
            }), n
        }
    }();
    E.mq = j;
    var z = (E.testStyles = f, E._config.usePrefixes ? w.split(" ") : []);
    E._cssomPrefixes = z;
    var P = function(t) {
        var r, o = b.length,
            i = e.CSSRule;
        if ("undefined" == typeof i) return n;
        if (!t) return !1;
        if (t = t.replace(/^@/, ""), r = t.replace(/-/g, "_").toUpperCase() + "_RULE", r in i) return "@" + t;
        for (var a = 0; o > a; a++) {
            var s = b[a],
                l = s.toUpperCase() + "_" + r;
            if (l in i) return "@-" + s.toLowerCase() + "-" + t
        }
        return !1
    };
    E.atRule = P;
    var k = {
        elem: l("modernizr")
    };
    Modernizr._q.push(function() {
        delete k.elem
    });
    var F = {
        style: k.elem.style
    };
    Modernizr._q.unshift(function() {
        delete F.style
    });
    E.testProp = function(e, t, r) {
        return v([e], n, t, r)
    };
    E.testAllProps = g;
    var M = E.prefixed = function(e, t, n) {
        return 0 === e.indexOf("@") ? P(e) : (-1 != e.indexOf("-") && (e = a(e)), t ? g(e, t, n) : g(e, "pfx"))
    };
    E.prefixedCSS = function(e) {
        var t = M(e);
        return t && s(t)
    };
    E.testAllProps = y, Modernizr.addTest("csstransitions", y("transition", "all", !0)), o(), i(S), delete E.addTest, delete E.addAsyncTest;
    for (var $ = 0; $ < Modernizr._q.length; $++) Modernizr._q[$]();
    e.Modernizr = Modernizr
}(window, document);