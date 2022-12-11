import React from 'react';
import Recieve from './Recieve';
import Send from './Send';
import { BrowserRouter, Routes, Route } from "react-router-dom";
const App = () => {

  return (
    <div className='App'>
      <BrowserRouter>
        <Routes>
        <Route path="recieve" element={<Recieve />} />
          <Route path="send" element={<Send />}>

          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;