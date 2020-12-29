<template>
  <div id="app">
    <div class="container">
      <div id="buttons">
        <button id="circle" class="btn" @click="chooseCirc()">Circle</button>
        <button id="Ellipse" class="btn" @click="chooseEllipce()">
          Ellipse
        </button>
        <button id="Rectangle" class="btn" @click="chooseRec()">
          Rectangle
        </button>
        <button id="Square" class="btn" @click="chooseSquare()">Square</button>
        <button id="Triangle" class="btn" @click="chooseTriangle()">
          Triangle
        </button>
        <button id="Line" class="btn" @click="chooseLine()">Line</button>
        <div class="saving">
          <button id="save" class="btn" @click="fileName()">Save</button>
          <select id="selectedType">
            <option>json</option>
            <option>xml</option>
          </select>
        </div>
        <input id="fileInput" type="file" style="display:none" @change="load"/>
        <button id="load" class="btn" onclick="document.getElementById('fileInput').click()">Load</button>
        <button id="undo" class="btn" @click="Undo()">Undo</button>
        <button id="redo" class="btn" @click="Redo()">Redo</button>
        <button id="clear" class="btn" @click="clear()">Clear</button>
        <button id="copy" class="btn" @click="copy()">Copy</button>
        <button id="delete" class="btn" @click="deleteItem()">Delete</button>
       
      </div>
      <div>
        <div id="home">
          <div class="prop">
            <label>fill Color</label>
            <input
              type="color"
              id="fill"
              value="#FFFFFF"
              @change="myfillcolor($event)"
            />
          </div>
          <div class="prop">
            <label>stroke Color</label>
            <input type="color" id="stroke" @change="mystokecolor($event)" />
          </div>
        </div>
        <div id="board">
          <v-stage
            ref="stage"
            :config="stageSize"
            @mousemove="handleMouseMove"
            @mouseDown="handleMouseDown"
            @mouseUp="handleMouseUp"
            @dragstart="handledragDown"
            @dragend="handleDragend"
            @dblclick="handleStageMouseDown"
          >
            <v-layer ref="layer">
              <v-circle
                v-for="circle in circles"
                :key="circle.id"
                @trasnsformend="handleTransformEnd"
                @mouseover="handleMouseOver"
                @mouseout="handleMouseOut"
                :config="{
                  id: circle.id,
                  x: circle.startPointX,
                  y: circle.startPointY,
                  radius: Math.sqrt(
                    Math.pow(circle.width, 2) + Math.pow(circle.height, 2)
                  ),
                  fill: circle.colorfill,
                  stroke: circle.colorstroke,
                  strokeWidth: 3,
                  scaleX: circle.scaleX,
                  scaleY: circle.scaleY,
                  rotation: circle.rotation,
                  draggable: true,
                }"
              />
              <v-ellipse
                v-for="ellipse in ellipses"
                :key="ellipse.id"
                @transformend="handleTransformEnd"
                @mouseover="handleMouseOver"
                @mouseout="handleMouseOut"
                :config="{
                  id: ellipse.id,
                  x: ellipse.startPointX,
                  y: ellipse.startPointY,
                  radiusX: Math.sqrt(
                    Math.pow(ellipse.width, 2) + Math.pow(ellipse.height, 2)
                  ),
                  radiusY:
                    Math.sqrt(
                      Math.pow(ellipse.width, 2) + Math.pow(ellipse.height, 2)
                    ) / 2,
                  fill: ellipse.colorfill,
                  stroke: ellipse.colorstroke,
                  strokeWidth: 3,
                  scaleX: ellipse.scaleX,
                  scaleY: ellipse.scaleY,
                  rotation: ellipse.rotation,
                  draggable: true,
                }"
              />
              <v-rect
                v-for="rec in recs"
                :key="rec.id"
                @transformend="handleTransformEnd"
                @mouseover="handleMouseOver"
                @mouseout="handleMouseOut"
                :config="{
                  id: rec.id,
                  x: Math.min(rec.startPointX, rec.startPointX + rec.width),
                  y: Math.min(rec.startPointY, rec.startPointY + rec.height),
                  width: Math.abs(rec.width),
                  height: Math.abs(rec.height),
                  fill: rec.colorfill,
                  stroke: rec.colorstroke,
                  strokeWidth: 3,
                  scaleX: rec.scaleX,
                  scaleY: rec.scaleY,
                  rotation: rec.rotation,
                  draggable: true,
                }"
              />
              <v-rect
                v-for="square in squares"
                :key="square.id"
                @transformend="handleTransformEnd"
                @mouseover="handleMouseOver"
                @mouseout="handleMouseOut"
                :config="{
                  id: square.id,
                  x: Math.min(
                    square.startPointX,
                    square.startPointX + square.width
                  ),
                  y: Math.min(
                    square.startPointY,
                    square.startPointY + square.height
                  ),
                  width: Math.abs(square.width),
                  height: Math.abs(square.width),
                  fill: square.colorfill,
                  stroke: square.colorstroke,
                  strokeWidth: 3,
                  scaleX: square.scaleX,
                  scaleY: square.scaleY,
                  rotation: square.rotation,
                  draggable: true,
                }"
              />
              <v-regular-polygon
                v-for="triangle in triangles"
                :key="triangle.id"
                @transformend="handleTransformEnd"
                @mouseover="handleMouseOver"
                @mouseout="handleMouseOut"
                :config="{
                  id: triangle.id,
                  x: triangle.startPointX,
                  y: triangle.startPointY,
                  sides: 3,
                  scaleX: triangle.scaleX,
                  scaleY: triangle.scaleY,
                  rotation: triangle.rotation,
                  radius: triangle.width,
                  fill: triangle.colorfill,
                  stroke: triangle.colorstroke,
                  strokeWidth: 3,
                  draggable: true,
                }"
              />

              <v-line
                v-for="line in lines"
                :key="line.id"
                @transformend="handleTransformEnd"
                @mouseover="handleMouseOver"
                @mouseout="handleMouseOut"
                :config="{
                  id: line.id,
                  points: [
                    line.startPointX,
                    line.startPointY,
                    line.startPointX + line.width,
                    line.startPointY + line.height,
                  ],
                  scaleX: line.scaleX,
                  scaleY: line.scaleY,
                  rotation: line.rotation,
                  fill: fillColor,
                  stroke: line.colorstroke,
                  strokeWidth: 3,
                  draggable: true,
                }"
              />

              <v-transformer ref="transformer" />
            </v-layer>
          </v-stage>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const width = window.innerWidth;
const height = window.innerHeight;
export default {
  data() {
    return {
      stageSize: {
        width: width,
        height: height,
      },
      isCorrectDrawing: true,
      isDrawing: false,
      isRec: false,
      isCirc: false,
      isEllipse: false,
      isSquare: false,
      isTriangle: false,
      isLine: false,
      isMoved: false,
      draggedItemId: "",
      draggedItem: null,
      recs: [],
      circles: [],
      ellipses: [],
      squares: [],
      triangles: [],
      lines: [],
      fillColor: "#ffffff",
      strokeColor: "black",
      startdragX: null,
      startdragY: null,
      isSelected: false,
      selectedItemId: "",
      selectedItem: null,
      temp: null,
      type: "",
      fileSelected:null,
    };
  },
  
  methods: {
    getShapes(){
      fetch("http://localhost:8085/circles", {
        method: "GET",
      })
        .then((response) => response.json())
        .then((data) => (this.circles = data));

      fetch("http://localhost:8085/rectangles", {
        method: "GET",
      })
        .then((response) => response.json())
        .then((data) => (this.recs = data));

      fetch("http://localhost:8085/elipses", {
        method: "GET",
      })
        .then((response) => response.json())
        .then((data) => (this.ellipses = data));

      fetch("http://localhost:8085/triangles", {
        method: "GET",
      })
        .then((response) => response.json())
        .then((data) => (this.triangles = data));

      fetch("http://localhost:8085/lines", {
        method: "GET",
      })
        .then((response) => response.json())
        .then((data) => (this.lines = data));

      fetch("http://localhost:8085/squares", {
        method: "GET",
      })
        .then((response) => response.json())
        .then((data) => (this.squares = data));
    },

    deleteItem(){
      if (this.selectedItemId != null)
      {
        fetch("http://localhost:8085/" + this.selectedItemId, {
        method: "DELETE",
      });
      this.getShapes();
      }
    },
    copy(){
      if (this.selectedItemId != null)
      {
        fetch("http://localhost:8085/copy/" + this.selectedItemId, {
        method: "POST",
      });
     this.getShapes();

      }
      

    },
    Undo() {
      fetch("http://localhost:8085/undo", {
        method: "POST",
      });
      this.getShapes();
    },
    Redo() {
      fetch("http://localhost:8085/redo", {
        method: "POST",
      });
      this.getShapes();
    },
    fileName() {
      const filename = prompt("Please enter file name");
      if (filename != null) {
        const filetype = document.getElementById("selectedType").value;
        fetch("http://localhost:8085/save/" + filename + "/" + filetype, {
          method: "POST",
        });
        
      }
    },
   async load(e) {
       var status =false;
        this.fileSelected=await e.target.files[0];
        let response=await fetch("http://localhost:8085/load/" +this.fileSelected.name, 
        {method: "POST",}
        );
        let data = await response.json();
        status =await  data;
       if(status){ 
        fetch("http://localhost:8085/circles", {
        method: "GET",
      })
        .then((response) => response.json())
        .then((data) => (this.circles = data));

      fetch("http://localhost:8085/rectangles", {
        method: "GET",
      })
        .then((response) => response.json())
        .then((data) => (this.recs = data));

      fetch("http://localhost:8085/elipses", {
        method: "GET",
      })
        .then((response) => response.json())
        .then((data) => (this.ellipses = data));

      fetch("http://localhost:8085/triangles", {
        method: "GET",
      })
        .then((response) => response.json())
        .then((data) => (this.triangles = data));

      fetch("http://localhost:8085/lines", {
        method: "GET",
      })
        .then((response) => response.json())
        .then((data) => (this.lines = data));

      fetch("http://localhost:8085/squares", {
        method: "GET",
      })
        .then((response) => response.json())
        .then((data) => (this.squares = data));
       }else{
         alert("unseccesful load");
       }  
        this.fileSelected=null;
    },
    async handleStageMouseDown(e) {
      // clicked on stage - clear selection
      if (e.target === e.target.getStage()) {
        this.selectedItemId = null;
        this.selectedItem = null;
        this.isSelected = false;
        this.updateTransformer();
        return;
      }
      // clicked on transformer - do nothing
      const clickedOnTransformer =
        e.target.getParent().className === "Transformer";
      if (clickedOnTransformer) {
        return;
      }
      // find clicked shape by its id
      this.selectedItemId = await e.target.id();
      this.isSelected = true;
      this.updatecolor();
      this.updateTransformer();
    },

    updateTransformer() {
      // here we need to manually attach or detach Transformer node
      const transformerNode = this.$refs.transformer.getNode();
      const stage = transformerNode.getStage();
      const { selectedItemId } = this;
      const selectedNode = stage.findOne("#" + selectedItemId);
      // do nothing if selected node is already attached
      if (selectedNode === transformerNode.node()) {
        return;
      }

      if (selectedNode) {
        // attach to another node
        transformerNode.nodes([selectedNode]);
      } else {
        // remove transformer
        transformerNode.nodes([]);
      }
      transformerNode.getLayer().batchDraw();
    },
    handleTransformEnd(e) {
      // shape is transformed, let us save new attrs back to the node
      // find element in our state
      var item = null;
      if (this.selectedItemId[0] == "c") {
        item = this.circles.find((i) => i.id === this.selectedItemId);
        const index = this.circles.indexOf(item);
        this.circles[index].startPointX = e.target.x();
        this.circles[index].startPointY = e.target.y();
        this.circles[index].scaleX = e.target.scaleX();
        this.circles[index].scaleY = e.target.scaleY();
      } else if (this.selectedItemId[0] == "e") {
        item = this.ellipses.find((i) => i.id === this.selectedItemId);
        const index = this.ellipses.indexOf(item);
        this.ellipses[index].startPointX = e.target.x();
        this.ellipses[index].startPointY = e.target.y();
        this.ellipses[index].scaleX = e.target.scaleX();
        this.ellipses[index].scaleY = e.target.scaleY();
      } else if (this.selectedItemId[0] == "s") {
        item = this.squares.find((i) => i.id === this.selectedItemId);
        const index = this.squares.indexOf(item);
        this.squares[index].startPointX = e.target.x();
        this.squares[index].startPointY = e.target.y();
        this.squares[index].scaleX = e.target.scaleX();
        this.squares[index].scaleY = e.target.scaleY();
      } else if (this.selectedItemId[0] == "r") {
        item = this.recs.find((i) => i.id === this.selectedItemId);
        const index = this.recs.indexOf(item);
        this.recs[index].startPointX = e.target.x();
        this.recs[index].startPointY = e.target.y();
        this.recs[index].scaleX = e.target.scaleX();
        this.recs[index].scaleY = e.target.scaleY();
      } else if (this.selectedItemId[0] == "t") {
        item = this.triangles.find((i) => i.id === this.selectedItemId);
        const index = this.triangles.indexOf(item);
        this.triangles[index].startPointX = e.target.x();
        this.triangles[index].startPointY = e.target.y();
        this.triangles[index].scaleX = e.target.scaleX();
        this.triangles[index].scaleY = e.target.scaleY();
      } else if (this.selectedItemId[0] == "l") {
        item = this.lines.find((i) => i.id === this.selectedItemId);
        const index = this.lines.indexOf(item);
        this.lines[index].scaleX = e.target.scaleX();
        this.lines[index].scaleY = e.target.scaleY();
      }
      if (!item != null) {
        fetch("http://localhost:8085/", {
          method: "POST",
          body: JSON.stringify(item),
        });
      }
    },

    async updatecolor() {
      var item = null;
      if (this.selectedItemId[0] == "c") {
        item = this.circles.find((i) => i.id === this.selectedItemId);
        const index = this.circles.indexOf(item);
        this.circles[index].colorfill = this.fillColor;
        this.circles[index].colorstroke = this.strokeColor;
      } else if (this.selectedItemId[0] == "e") {
        item = this.ellipses.find((i) => i.id === this.selectedItemId);
        const index = this.ellipses.indexOf(item);
        this.ellipses[index].colorfill = this.fillColor;
        this.ellipses[index].colorstroke = this.strokeColor;
      } else if (this.selectedItemId[0] == "s") {
        item = this.squares.find((i) => i.id === this.selectedItemId);
        const index = this.squares.indexOf(item);
        this.squares[index].colorfill = this.fillColor;
        this.squares[index].colorstroke = this.strokeColor;
      } else if (this.selectedItemId[0] == "r") {
        item = this.recs.find((i) => i.id === this.selectedItemId);
        const index = this.recs.indexOf(item);
        this.recs[index].colorfill = this.fillColor;
        this.recs[index].colorstroke = this.strokeColor;
      } else if (this.selectedItemId[0] == "t") {
        item = this.triangles.find((i) => i.id === this.selectedItemId);
        const index = this.triangles.indexOf(item);
        this.triangles[index].colorfill = this.fillColor;
        this.triangles[index].colorstroke = this.strokeColor;
      } else if (this.selectedItemId[0] == "l") {
        item = this.lines.find((i) => i.id === this.selectedItemId);
        const index = this.lines.indexOf(item);
        this.lines[index].colorstroke = this.strokeColor;
      }
      if (item != null) {
        fetch("http://localhost:8085/", {
          method: "POST",
          body: JSON.stringify(item),
        });
      }
    },
    handleMouseOver() {
      this.isCorrectDrawing = false;
    },
    handleMouseOut() {
      this.isCorrectDrawing = true;
    },
    async handledragDown(e) {
      //select stage -- remove selection
      if (e.target === e.target.getStage()) {
        this.draggedItemId = "";
        this.draggedItem = null;
        return;
      }
      //posision of start drag
      const pos = this.$refs.stage.getNode().getPointerPosition();
      this.startdragX = pos.x;
      this.startdragY = pos.y;
      // find clicked item by its id
      this.draggedItemId = await e.target.id();
    },
    handleDragend() {
      //posision of end drag
      const pos = this.$refs.stage.getNode().getPointerPosition();
      const w = pos.x - this.startdragX;
      const h = pos.y - this.startdragY;
      var item = null;
      if (this.draggedItemId[0] == "c") {
        item = this.circles.find((i) => i.id === this.draggedItemId);
        const index = this.circles.indexOf(item);
        this.circles[index].startPointX += w;
        this.circles[index].startPointY += h;
      } else if (this.draggedItemId[0] == "e") {
        item = this.ellipses.find((i) => i.id === this.draggedItemId);
        const index = this.ellipses.indexOf(item);
        this.ellipses[index].startPointX += w;
        this.ellipses[index].startPointY += h;
      } else if (this.draggedItemId[0] == "r") {
        item = this.recs.find((i) => i.id === this.draggedItemId);
        const index = this.recs.indexOf(item);
        this.recs[index].startPointX += w;
        this.recs[index].startPointY += h;
      } else if (this.draggedItemId[0] == "s") {
        item = this.squares.find((i) => i.id === this.draggedItemId);
        const index = this.squares.indexOf(item);
        this.squares[index].startPointX += w;
        this.squares[index].startPointY += h;
      } else if (this.draggedItemId[0] == "t") {
        item = this.triangles.find((i) => i.id === this.draggedItemId);
        const index = this.triangles.indexOf(item);
        this.triangles[index].startPointX += w;
        this.triangles[index].startPointY += h;
      } else if (this.draggedItemId[0] == "l") {
        item = this.lines.find((i) => i.id === this.draggedItemId);
        const index = this.lines.indexOf(item);
        this.lines[index].startPointX += w / 100;
        this.lines[index].startPointY += h / 100;
      }
      if (item != null) {
        fetch("http://localhost:8085/", {
          method: "POST",
          body: JSON.stringify(item),
        });
      }
      this.draggedItemId = "";
      this.draggedItem = null;
      this.startdragX = null;
      this.startdragY = null;
    },
    async myfillcolor(e) {
      this.fillColor = await e.target.value;
    },
    async mystokecolor(e) {
      this.strokeColor = await e.target.value;
    },
    noShapeChosen() {
      this.isRec = false;
      this.isCirc = false;
      this.isEllipse = false;
      this.isSquare = false;
      this.isTriangle = false;
      this.isLine = false;
      this.type = "";
    },
    chooseRec() {
      this.noShapeChosen();
      this.isRec = true;
      this.type = "rectangle";
    },
    chooseSquare() {
      this.noShapeChosen();
      this.isSquare = true;
      this.type = "square";
    },
    chooseCirc() {
      this.noShapeChosen();
      this.isCirc = true;
      this.type = "circle";
    },
    chooseEllipce() {
      this.noShapeChosen();
      this.isEllipse = true;
      this.type = "elipse";
    },
    chooseTriangle() {
      this.noShapeChosen();
      this.isTriangle = true;
      this.type = "triangle";
    },
    chooseLine() {
      this.noShapeChosen();
      this.isLine = true;
      this.type = "line";
    },
    handleMouseDown() {
      const pos = this.$refs.stage.getNode().getPointerPosition();
      var fillcolour = this.fillColor,
        strokecolour = this.strokeColor;
      if (this.isCorrectDrawing && !this.isSelected) {
        this.isDrawing = true;
        if (this.isRec) {
          this.setRecs([
            ...this.recs,
            {
              id: "r" + this.recs.length + "",
              startPointX: pos.x,
              startPointY: pos.y,
              width: 0,
              height: 0,
              scaleX: 1,
              scaleY: 1,
              rotation: 0,
              colorfill: fillcolour,
              colorstroke: strokecolour,
            },
          ]);
        } else if (this.isCirc) {
          this.setCircles([
            ...this.circles,
            {
              id: "c" + this.circles.length + "",
              startPointX: pos.x,
              startPointY: pos.y,
              width: 0,
              height: 0,
              scaleX: 1,
              scaleY: 1,
              rotation: 0,
              colorfill: fillcolour,
              colorstroke: strokecolour,
            },
          ]);
        } else if (this.isEllipse) {
          this.setEllipses([
            ...this.ellipses,
            {
              id: "e" + this.ellipses.length + "",
              startPointX: pos.x,
              startPointY: pos.y,
              width: 0,
              height: 0,
              scaleX: 1,
              scaleY: 1,
              rotation: 0,
              colorfill: fillcolour,
              colorstroke: strokecolour,
            },
          ]);
        } else if (this.isSquare) {
          this.setSquares([
            ...this.squares,
            {
              id: "s" + this.squares.length + "",
              startPointX: pos.x,
              startPointY: pos.y,
              width: 0,
              height: 0,
              scaleX: 1,
              scaleY: 1,
              rotation: 0,
              colorfill: fillcolour,
              colorstroke: strokecolour,
            },
          ]);
        } else if (this.isTriangle) {
          this.setTraingles([
            ...this.triangles,
            {
              id: "t" + this.triangles.length + "",
              startPointX: pos.x,
              startPointY: pos.y,
              width: 0,
              height: 0,
              scaleX: 1,
              scaleY: 1,
              rotation: 0,
              colorfill: fillcolour,
              colorstroke: strokecolour,
            },
          ]);
        } else if (this.isLine) {
          this.setLines([
            ...this.lines,
            {
              id: "l" + this.lines.length + "",
              startPointX: pos.x,
              startPointY: pos.y,
              scaleX: 1,
              scaleY: 1,
              rotation: 0,
              width: 0,
              height: 0,
              colorfill: fillcolour,
              colorstroke: strokecolour,
            },
          ]);
        }
      }
    },

    async handleMouseUp() {
      this.isDrawing = false;
      if (this.temp != null) {
        await fetch("http://localhost:8085/" + this.type + "/" + this.temp.id, {
          method: "POST",
        });
        fetch("http://localhost:8085/", {
          method: "POST",
          body: JSON.stringify(this.temp),
        });
        if (!this.isMoved) {
          this.noShapeChosen();
        }
        this.isMoved = false;
        this.temp = null;
      }
    },
    setCircles(element) {
      this.circles = element;
    },
    setRecs(element) {
      this.recs = element;
    },
    setEllipses(element) {
      this.ellipses = element;
    },
    setSquares(element) {
      this.squares = element;
    },
    setTraingles(element) {
      this.triangles = element;
    },
    setLines(element) {
      this.lines = element;
    },
    handleMouseMove() {
      // no drawing - skipping
      if (!this.isDrawing) {
        return;
      }
      const point = this.$refs.stage.getNode().getPointerPosition();
      this.isMoved = true;
      // handle  rectangle part
      if (this.isRec) {
        let curRec = this.recs[this.recs.length - 1];
        curRec.width = point.x - curRec.startPointX;
        curRec.height = point.y - curRec.startPointY;
        this.temp = curRec;
      }
      // handle  circle part
      else if (this.isCirc) {
        let curCirc = this.circles[this.circles.length - 1];
        curCirc.width = point.x - curCirc.startPointX;
        curCirc.height = point.y - curCirc.startPointY;
        this.temp = curCirc;
      }
      // handle ellipse part
      else if (this.isEllipse) {
        let curElipse = this.ellipses[this.ellipses.length - 1];
        curElipse.width = point.x - curElipse.startPointX;
        curElipse.height = point.y - curElipse.startPointY;
        this.temp = curElipse;
      }
      //handle square part
      else if (this.isSquare) {
        let curSquare = this.squares[this.squares.length - 1];
        curSquare.width = point.x - curSquare.startPointX;
        curSquare.height = point.y - curSquare.startPointY;
        this.temp = curSquare;
      } // handle triangle part
      else if (this.isTriangle) {
        let curTraingle = this.triangles[this.triangles.length - 1];
        curTraingle.width = point.x - curTraingle.startPointX;
        curTraingle.height = point.y - curTraingle.startPointY;
        this.temp = curTraingle;
      } // handle line part
      else if (this.isLine) {
        let curline = this.lines[this.lines.length - 1];
        curline.width = point.x - curline.startPointX;
        curline.height = point.y - curline.startPointY;
        this.temp = curline;
      }
    },
    clear() {
      fetch("http://localhost:8085/deleteAll", {
        method: "DELETE",});

     this.getShapes();
    },
  },
};
</script>

<style>
#app {
  width: 100%;
  height: 200%;
  min-height: 730px;
  background-image: linear-gradient(to bottom, #038fa1, #023e6e);
  display: flex;
  justify-content: center;
  align-items: center;
}
.container {
  width: 85%;
  max-width: 1300px;
  height: 700px;
  background-image: linear-gradient(to bottom, #004d59, #012024);
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 25px 35px -15px rgba(3, 28, 31, 0.85);
  /*flex*/
  display: grid;
  grid-template-columns: auto auto;
  grid-template-columns: 120px 900px;
  justify-content: space-around;
  align-items: center;
  align-content: space-around;
}
#buttons {
  display: grid;
  height: 650px;
  width: 150px;
  align-content: space-around;
  justify-content: space-around;
  margin-top: 70px;
}
.btn {
  cursor: pointer;
  font-size: 16px;
  text-align: center;
  width: 100px;
  height: 30px;
  color: black;
  background-image: linear-gradient(to bottom, #038fa1, #58adf3);
  border-radius: 10px;
}

#board {
  width: 80%;
  width: 900px;
  max-width: 1000px;
  background-color: azure;
  max-height: 650px;
  overflow: hidden;
  resize: both;
}
#home {
  width: 50%;
  height: 80;
  color: #dddddd;
  display: flex;
  margin-bottom: 10px;
}
.prop {
  margin-right: 40px;
}
label {
  margin-right: 10px;
  vertical-align: 10%;
}
.saving {
  display: flex;
  flex-direction: row;
}
select {
  background-image: linear-gradient(to bottom, #038fa1, #58adf3);
  margin-left: 10px;
}
</style>
