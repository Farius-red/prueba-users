import { PlantillaResponse } from './../../../../node_modules/juliaositembackenexpress/src/utils/PlantillaResponse';
import { RegisterUserDTO } from './../../../../node_modules/juliaositembackenexpress/src/api/dtos/RegisterUserDTO';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { JuliaoSystemCrudHttpService } from './juliaoSystemCrudHttpService';
import { environment } from '../../../environments/environment';



@Injectable({
  providedIn: 'root'
})
export class UsuarioService extends JuliaoSystemCrudHttpService<PlantillaResponse<RegisterUserDTO>> {
  


  constructor(
    protected override http: HttpClient,
  ) {
    super(http);
    this.basePathUrl = environment.baseUrls+"/user/";
  }


  

getUser():Observable<PlantillaResponse<RegisterUserDTO>>{

 
  return  this.http.get<PlantillaResponse<RegisterUserDTO>>(this.basePathUrl+"/get-user-log",{},);
};

}
