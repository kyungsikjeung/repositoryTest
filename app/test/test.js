var rootVM = null;

function TestViewModel() {

	var self = this;
	self.form = $("#testForm");
	
	$.getJSON("/resources/json/seoul_gson.json", function(geojson){
		
		var data = geojson.features;
		var coordinates = [];	//좌표 저장할 배열
		var name = '';			//행정 구 이름
		
		$.each(data, function(index, val){
			
			coordinates = val.geometry.coordinates;
			name = val.properties.SIG_KOR_NM;
			
			displayArea(coordinates, name);
			
		})
	})
	
	var polygons=[];	// function 안 쪽에 지역변수로 넣으니깐 폴리곤 한다 생성할 때마다 배열이 비어서 클릭했을 때 전체를 못 없애줌
	
	//행정구역 폴리곤
	function displayArea(coordinates, name){
		
		var path = [];		//폴리곤 그려줄 path
		var points = [];	//중심좌표 구하기 위한 지역구 좌표들
		
		$.each(coordinates[0], function(index, coordinate) {
			var point = new Object();
			point.x = coordinate[1];
			point.y = coordinate[0];
			points.push(point);
			path.push(new kakao.maps.LatLng(coordinate[1], coordinate[0]));
		});
		
		// 다각형을 생성합니다 
	    var polygon = new kakao.maps.Polygon({
	        map: map, // 다각형을 표시할 지도 객체
	        path: path,
	        strokeWeight: 2,
	        strokeColor: '#B35F63',
	        strokeOpacity: 0.8,
	        strokeStyle: 'dashed',
	        fillColor: '#70B381',
	        fillOpacity: 0.5
	    });
		
		polygons.push(polygon);		//폴리곤 제거를 위한 배열
		
		// 커스텀 오버레이를 생성합니다
		var customOverlay = new kakao.maps.CustomOverlay({
			
		});
		
		// 다각형에 mouseover 이벤트를 등록하고 이벤트가 발생하면 폴리곤의 채움색을 변경합니다 
	    // 지역명을 표시하는 커스텀오버레이를 지도위에 표시합니다
	    kakao.maps.event.addListener(polygon, 'mouseover', function(mouseEvent) {
	        polygon.setOptions({fillColor: '#09f'});

	        customOverlay.setContent('<div class="area">' + name + '</div>');
	        
	        customOverlay.setPosition(mouseEvent.latLng); 
	        customOverlay.setMap(map);
	    });
	    
	    // 다각형에 mousemove 이벤트를 등록하고 이벤트가 발생하면 커스텀 오버레이의 위치를 변경합니다 
	    kakao.maps.event.addListener(polygon, 'mousemove', function(mouseEvent) {
	        
	        customOverlay.setPosition(mouseEvent.latLng); 
	    });

	    // 다각형에 mouseout 이벤트를 등록하고 이벤트가 발생하면 폴리곤의 채움색을 원래색으로 변경합니다
	    // 커스텀 오버레이를 지도에서 제거합니다 
	    kakao.maps.event.addListener(polygon, 'mouseout', function() {
	        polygon.setOptions({fillColor: '#fff'});
	        customOverlay.setMap(null);
	    });
	    
	    // 다각형에 click 이벤트를 등록하고 이벤트가 발생하면 다각형의 이름과 면적을 인포윈도우에 표시합니다 
	    kakao.maps.event.addListener(polygon, 'click', function(mouseEvent) {
	    	
	    	// 현재 지도 레벨에서 2레벨 확대한 레벨
	    	var level = map.getLevel()-2;
	    	
	    	// 지도를 클릭된 폴리곤의 중앙 위치를 기준으로 확대합니다.
	    	
	    	map.setLevel(level, {anchor: centroid(points), animate: {
	    		duration: 350
	    	}});
	    	
	        deletePolygon(polygons);	//폴리곤제거
	        customOverlay.setMap(null);
	    });
		
	}
	
	//centroid 알고리즘 (폴리곤 중심좌표 구하기 위함)
	function centroid (points) {
		var i, j, len, p1, p2, f, area, x, y;
		
		area = x = y = 0;
		
		for(i=0, len=points.length, j=len-1; i<len; j = i++){
			p1 = points[i];
			p2 = points[j];
			
			f = p1.y * p2.x - p2.y * p1.x;
			x += (p1.x + p2.x) * f;
			y += (p1.y + p2.y) * f;
			area += f * 3;
		}
		
		return new kakao.maps.LatLng(x / area, y / area);
		
	}
	
	//지도 위 표시되고 있는 폴리곤 제거
	function deletePolygon(polygons){
		for (var i = 0; i < polygons.length; i++){
			polygons[i].setMap(null);
		}
		polygons = [];
	} 
	
	// 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
	var placeOverlay = new kakao.maps.CustomOverlay({zIndex:1}), 
	    contentNode = document.createElement('div'), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다 
	    markers = [], // 마커를 담을 배열입니다
		currCategory = ''; // 현재 선택된 카테고리를 가지고 있을 변수입니다
	
	var mapContainer = document.getElementById('map'), //지도를 담을 영역의 DOM 레퍼런스
		mapOptions = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(37.564671, 126.990582), //지도의 중심좌표.
			minLevel: 2,
			maxLevel: 8,
			level: 8 //지도의 레벨(확대, 축소 정도)
	};
	
	var map = new kakao.maps.Map(mapContainer, mapOptions); //지도 생성 및 객체 리턴
	
	// 지도가 이동, 확대, 축소로 인해 중심좌표가 변경되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
	kakao.maps.event.addListener(map, 'center_changed', function() {

	    // 지도의  레벨을 얻어옵니다
	    var level = map.getLevel();
	    

	    // 지도의 중심좌표를 얻어옵니다 
	    var latlng = map.getCenter(); 

	    var message = '<p>지도 레벨은 ' + level + ' 이고</p>';
	    message += '<p>중심 좌표는 위도 ' + latlng.getLat() + ', 경도 ' + latlng.getLng() + '입니다</p>';

	    var resultDiv = document.getElementById('result');
	    resultDiv.innerHTML = message;
	    
	});
	
	// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({zIndex:1});
	
	// 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다 
	contentNode.className = 'placeinfo_wrap';
	
	// 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
	// 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다 
	addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
	addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);
	
	// 커스텀 오버레이 컨텐츠를 설정합니다
	placeOverlay.setContent(contentNode);
	
	// 각 카테고리에 클릭 이벤트를 등록합니다
	addCategoryClickEvent();
	
	// 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
	function addEventHandle(target, type, callback) {
	    if (target.addEventListener) {
	        target.addEventListener(type, callback);
	    } else {
	        target.attachEvent('on' + type, callback);
	    }
	}
	
	// 각 카테고리에 클릭 이벤트를 등록합니다
	function addCategoryClickEvent() {
	    var category = document.getElementById('category'),
	        children = category.children;

	    for (var i=0; i<children.length; i++) {
	        children[i].onclick = onClickCategory;
	    }
	}
	
	// 카테고리를 클릭했을 때 호출되는 함수입니다
	function onClickCategory() {
	    var id = this.id,
	        className = this.className;

	    placeOverlay.setMap(null);
	    rootVM.orgPlaceOverlay(null);
	    
	    if(id == 'ALL') {
	    	deletePolygon(polygons);	//폴리곤제거
	    	$.getJSON("/resources/json/seoul_gson.json", function(geojson){
	    		
	    		var data = geojson.features;
	    		var coordinates = [];	//좌표 저장할 배열
	    		var name = '';			//행정 구 이름
	    		
	    		$.each(data, function(index, val){
	    			
	    			coordinates = val.geometry.coordinates;
	    			name = val.properties.SIG_KOR_NM;
	    			
	    			displayArea(coordinates, name);
	    			
	    		})
	    	})
	    } else {
	    	deletePolygon(polygons);	//폴리곤제거
	    }
	    
	    if (className === 'on') {
	        currCategory = '';
	        changeCategoryClass();
	        removeMarker();
	    } else {
	        currCategory = id;
	        changeCategoryClass(this);
	        displayPlaces(positions, currCategory);
	    }
	}

	// 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
	function changeCategoryClass(el) {
	    var category = document.getElementById('category'),
	        children = category.children,
	        i;

	    for ( i=0; i<children.length; i++ ) {
	        children[i].className = '';
	    }

	    if (el) {
	        el.className = 'on';
	    } 
	} 
	

	// 마커를 표시할 위치와 title 객체 배열입니다 
	var positions = [
	    {
	        title: '에이블 충무로(파견)', 
	        content: '서울 중구 퇴계로 213 일흥빌딩 3층',
	        tel: '02-6205-5008',
	        category: 'BO',
	        alert: true,
	        latlng: new kakao.maps.LatLng(37.561899, 126.995500)
	    },
	    {
	        title: '에이블 본사',
	        content: '서울 광진구 뚝섬로 691-1 모던빌딩 4층',
	        tel: '010-1234-5678',
	        category: 'HO',
	        alert: false,
	        latlng: new kakao.maps.LatLng(37.532876, 127.086237)
	    },
	    {
	    	title: '에이블 다남매',
	        content: '서울 성동구 고산자로 253',
	        tel: '010-1234-5678',
	        category: 'BO',
	        alert: false,
	        latlng: new kakao.maps.LatLng(37.562369, 127.035616)
	    },
	    {
	    	title: '에이블 목동(파견)',
	        content: '서울 양천구 목동동로 309 (행복한백화점)',
	        tel: '02-6678-9000',
	        category: 'BO',
	        alert: false,
	        latlng: new kakao.maps.LatLng(37.529135, 126.876005)
	    },
	    {
	    	title: '북한산우이역',
	        content: '서울 강북구 우이동 16-27',
	        tel: '02-3499-5561',
	        category: 'BO',
	        alert: false,
	        latlng: new kakao.maps.LatLng(37.662983, 127.012486)
	    },
	    {
	    	title: '코스트코 양재점',
	        content: '서울 서초구 양재대로 159',
	        tel: '1899-9900',
	        category: 'BO',
	        alert: false,
	        latlng: new kakao.maps.LatLng(37.461898, 127.036308)
	    }
	    
	];
	
	// 검색 결과 목록과 마커를 표출하는 함수입니다
	function displayPlaces(positions, currCategory) {
		
		if(currCategory != 'ALL' && _.isArray(positions)){
			positions = _.filter(positions, function(row){return row.category == currCategory});
		} 		
		var listEl = document.getElementById('placesList'), 
	    menuEl = document.getElementById('menu_wrap'),
	    fragment = document.createDocumentFragment(), 
	    bounds = new kakao.maps.LatLngBounds(), 
	    listStr = '';
	    
	    // 검색 결과 목록에 추가된 항목들을 제거합니다
	    removeAllChildNods(listEl);
	    
	    // 지도에 표시되고 있는 마커를 제거합니다
	    removeMarker();
		
		for (var i = 0; i < positions.length; i++) {
			
			// 마커를 생성하고 지도에 표시합니다
	        var marker = addMarker(positions[i].latlng, positions[i].title, positions[i].category, positions[i].alert);
	        itemEl = getListItem(i, positions[i]); // 검색 결과 항목 Element를 생성합니다
	        
	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        bounds.extend(positions[i].latlng);
	        
	        (function(marker, place, title) {
	        	// 마커와 검색결과 항목에 mouseover 했을때 해당 장소에 인포윈도우에 장소명을 표시합니다
	        	kakao.maps.event.addListener(marker, 'mouseover', function() {
	        		if(rootVM.orgPlaceOverlay() != place.latlng){
	       			 displayInfowindow(marker, title);
	       			 }
	            });
	        	// 마커와 검색결과 항목에 mouseout  했을때 해당 장소에 인포윈도우를 닫습니다
	        	 kakao.maps.event.addListener(marker, 'mouseout', function() {
	                 infowindow.close();
	             });
	        	 itemEl.onmouseover =  function () {
	        		 map.setCenter(place.latlng);
	        		 if(rootVM.orgPlaceOverlay() != place.latlng){
	        			 displayInfowindow(marker, title);
	        		 }
	             };
	
	             itemEl.onmouseout =  function () {
	                 infowindow.close();
	             };
	             
	             itemEl.onclick =  function () {
	            	 displayPlaceInfo(place);
	             };
	             
	            // 마커와 검색결과 항목을 클릭 했을 때 장소정보를 표출하도록 클릭 이벤트를 등록합니다
	            kakao.maps.event.addListener(marker, 'click', function() {
	                displayPlaceInfo(place);
	            });
	        })(marker, positions[i], positions[i].title);
	        fragment.appendChild(itemEl);
		}
		
		 // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
	    listEl.appendChild(fragment);
	    menuEl.scrollTop = 0;
	    
	    // 지도의 크기가 변경되었기 때문에 relayout 함수를 호출합니다
	    // 지도가 한번에 호출이 안되서 setTimeout 걸어서 비동기 처리
	    setTimeout(function(){ map.relayout(); }, 100);
	    
	    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	    setTimeout(function(){ map.setBounds(bounds); }, 100);
	    
	    
        
	    
	}
	
	// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
	function makeOverListener(map, marker, infowindow) {
	    return function() {
	        infowindow.open(map, marker);
	    };
	}
	
	// 검색결과 항목을 Element로 반환하는 함수입니다
	function getListItem(index, places) {

	    var el = document.createElement('li'),
	    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
	                '<div class="info">' +
	                '   <h5>' + places.title + '</h5>';
	        itemStr += '    <span>' +  places.content  + '</span>'; 
	      itemStr += '  <span class="tel">' + places.tel  + '</span>' +
	                '</div>';
	      
	    el.innerHTML = itemStr;
	    el.className = 'item';

	    return el;
	}
	
	// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
	function addMarker(position, title, category, alert) {
		if(category == 'HO'){
			var imageSrc = '/images/map_maker/maker_HO.gif' // 마커 이미지 url, 스프라이트 이미지를 씁니다
		} else {
			var imageSrc = '/images/map_maker/maker_tent.gif' // 마커 이미지 url, 스프라이트 이미지를 씁니다
		}
		if(alert == true){
			var imageSrc = '/images/map_maker/maker_siren.gif' // 마커 이미지 url, 스프라이트 이미지를 씁니다
		} 
	        var imageSize = new kakao.maps.Size(80, 80),  // 마커 이미지의 크기
	        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize),
	            marker = new kakao.maps.Marker({
	            position: position, // 마커의 위치
	            image: markerImage 
	        });

	    marker.setMap(map); // 지도 위에 마커를 표출합니다
	    markers.push(marker);  // 배열에 생성된 마커를 추가합니다
//	    map.setCenter(position);

	    return marker;
	}
	
	// 지도 위에 표시되고 있는 마커를 모두 제거합니다
	function removeMarker() {
	    for ( var i = 0; i < markers.length; i++ ) {
	        markers[i].setMap(null);
	    }   
	    markers = [];
	}
	
	self.orgPlaceOverlay = ko.observable(null);

	// 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
	function displayPlaceInfo(place){
		
		var content =
		'<div class="placeinfo">' +
        '   <a class="title" onclick="rootVM.closeOverlay()" title="' + place.title + '">' + place.title + '</a>';   
		content += '    <span title="' + place.content + '">' + place.content + '</span>';
		content += '    <span class ="tel">' + place.tel + '</span>' + 
		    '</div>' + 
		    '<div class="after"></div>';
		
		contentNode.innerHTML = content;
        placeOverlay.setPosition(place.latlng);
        rootVM.orgPlaceOverlay(place.latlng);
        placeOverlay.setMap(map);
        deletePolygon(polygons);	//폴리곤제거
        
        map.setCenter(place.latlng);
        // setLevel 시 애니메이션 효과의 지속시간을 500ms로 설정
        map.setLevel(2, { animate: true});
	    
	}
	
	// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
	self.closeOverlay = function() {
		placeOverlay.setMap(null);
		rootVM.orgPlaceOverlay(null);
	}
	
	// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
	// 인포윈도우에 장소명을 표시합니다
	function displayInfowindow(marker, title) {
	    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

	    infowindow.setContent(content);
	    infowindow.open(map, marker);
	}

	 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
	function removeAllChildNods(el) {   
	    while (el.hasChildNodes()) {
	        el.removeChild (el.lastChild);
	    }
	}
	
	self.init = function() {
		
//		var myPlace = _.filter(positions, function(row){return row.title == '에이블 충무로(파견)'});
//		var myPlace = positions;
//		displayPlaces(myPlace, 'ALL');
		$("#ALL").trigger("click")
	};
}

$(document).ready(function() {
	rootVM = new TestViewModel();
	rootVM.init();
	
	ko.applyBindings(rootVM);
});