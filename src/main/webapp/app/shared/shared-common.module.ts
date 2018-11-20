import { NgModule } from '@angular/core';

import { CuidebemappSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [CuidebemappSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [CuidebemappSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class CuidebemappSharedCommonModule {}
