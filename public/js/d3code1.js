export default function define(runtime, observer) {
  const main = runtime.module();

  main.variable(observer("chart")).define("chart", ["data","d3","width","height","DOM","drag"], function(data,d3,width,height,DOM,drag)
{
  const links = data.links.map(d => Object.create(d));
  const nodes = data.nodes.map(d => Object.create(d));
  
  const simulation = d3.forceSimulation(nodes)
      .force("link", d3.forceLink(links).id(d => d.id).distance(200))
      .force("charge", d3.forceManyBody())
      .force("center", d3.forceCenter(width / 2, height / 2))
      .on("tick", ticked);
  
  const svg = d3.select(DOM.svg(width, height));
  
  const link = svg.append("g")
      .attr("stroke", "#5e5e5e")
      .attr("stroke-opacity", 0.8)
      .attr("marker-end", "url(#arrow)")
    .selectAll("line")
    .data(links)
    .enter().append("line")
      .attr("stroke-width", d => Math.sqrt(d.value));
  
  const node = svg.append("g")
      .attr("stroke", "#fff")
      .attr("stroke-width", 1.5)
    .selectAll("circle")
    .data(nodes)
    .enter().append("circle")
      .attr("r", 6)
      .attr("fill", "#ff2600")
      .call(drag(simulation));
  

svg.append("defs").append("marker")
    .attr("id", "arrow")
    .attr("viewBox", "0 -5 10 10")
    .attr("refX", 20)
    .attr("refY", 0)
    .attr("opacity", .9)
    .attr("markerWidth", 8)
    .attr("markerHeight", 8)
    .attr("orient", "auto")
    .attr("fill", "#5e5e5e")
  .append("svg:path")
    .attr("d", "M0,-5L10,0L0,5");

  node.append("title")
      .text(d => d.id);
  
const text = svg.append("g").attr("class", "labels").selectAll("g")
    .data(nodes)
  .enter().append("g");

text.append("text")
    .attr("x", 5)
    .attr("y", -5)
    .attr("opacity", 1)
    .attr("pointer-events", "none")
    .style("font-family", "sans-serif")
    .style("font-size", "0.7em")
    .text(function(d) { return d.id; })
  
  function ticked() {
    link
        .attr("x1", d => d.source.x)
        .attr("y1", d => d.source.y)
        .attr("x2", d => d.target.x)
        .attr("y2", d => d.target.y);
    
    node
        .attr("cx", d => d.x)
        .attr("cy", d => d.y);
    
     text
        .attr("transform",
              function(d) { return "translate(" + d.x + "," + d.y + ")"; })
  }
  
  return svg.node();
}
);
  main.variable(observer("data")).define("data", function(){return(
{
    "directed": false,
    "multigraph": true,
    "graph": {},
    "nodes": [
        {
            "id": "13"
        },
        {
            "id": "0"
        },
        {
            "id": "1"
        },
        {
            "id": "2"
        },
        {
            "id": "3"
        },
        {
            "id": "4"
        },
        {
            "id": "5"
        },
        {
            "id": "6"
        },
        {
            "id": "7"
        },
        {
            "id": "8"
        },
        {
            "id": "9"
        },
        {
            "id": "10"
        },
        {
            "id": "11"
        }
    ],
    "links": [
        {
            "source": "1",
            "target": "2"
        },
        {
            "source": "2",
            "target": "3"
        },
        {
            "source": "3",
            "target": "4"
        },
        {
            "source": "3",
            "target": "5"
        },
        {
            "source": "6",
            "target": "2"
        },
        {
            "source": "8",
            "target": "7"
        },
        {
            "source": "4",
            "target": "1"
        },
        {
            "source": "3",
            "target": "8"
        },
        {
            "source": "7",
            "target": "3"
        },
        {
            "source": "2",
            "target": "5"
        }
    ]
}
)});
  main.variable(observer("height")).define("height", function(){return(
600
)});
  main.variable(observer("color")).define("color", ["d3"], function(d3)
{
  const scale = d3.scaleOrdinal(d3.schemeCategory10);
  return d => scale(d.group);
}
);
  main.variable(observer("drag")).define("drag", ["d3"], function(d3){return(
simulation => {
  
  function dragstarted(d) {
    if (!d3.event.active) simulation.alphaTarget(0.3).restart();
    d.fx = d.x;
    d.fy = d.y;
  }
  
  function dragged(d) {
    d.fx = d3.event.x;
    d.fy = d3.event.y;
  }
  
  function dragended(d) {
    if (!d3.event.active) simulation.alphaTarget(0);
    d.fx = null;
    d.fy = null;
  }
  
  return d3.drag()
      .on("start", dragstarted)
      .on("drag", dragged)
      .on("end", dragended);
}
)});
  main.variable(observer("d3")).define("d3", ["require"], function(require){return(
require("https://d3js.org/d3.v5.min.js")
)});
  return main;
}
